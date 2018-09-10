package org.tc.shiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.redis.cache.RedisCacheDao;
import org.tc.shiro.core.dto.ExecutionResult;
import org.tc.shiro.core.dto.Exposer;
import org.tc.shiro.core.enums.SeckillStateEnum;
import org.tc.shiro.core.exception.RepeatKillException;
import org.tc.shiro.core.exception.SeckillClosedException;
import org.tc.shiro.core.exception.SeckillException;
import org.tc.shiro.core.shiroext.kit.ShiroKit;
import org.tc.shiro.mapper.SeckillMapper;
import org.tc.shiro.mapper.SeckillResultMapper;
import org.tc.shiro.po.Seckill;
import org.tc.shiro.po.SeckillResult;
import org.tc.shiro.service.ISeckillService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Redinw
 * Description:
 */
@Slf4j
@Service
public class SeckillServiceImpl extends BaseServiceImpl<SeckillMapper, Seckill> implements ISeckillService {

    public static final String salt = "test";

    @Autowired
    private SeckillResultMapper seckillResultMapper;
    @Autowired
    private RedisCacheDao redisCacheDao;

    @Override
    public PageInfo<Seckill> page(Seckill seckill, Integer pageNo, Integer pageSize, String sort) {

        if (StringUtils.isBlank(seckill.getName())) {
            seckill.setName(null);
        }
        PageHelper.startPage(pageNo, pageSize, sort);
        List<Seckill> list = baseMapper.list(seckill);
        PageInfo<Seckill> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public Exposer exoportSeckillUrl(long seckillId) {
        Seckill seckill = (Seckill) redisCacheDao.get("seckill", seckillId);
        if (seckill == null) {
            seckill = baseMapper.selectByPrimaryKey(seckillId);
            if (seckill == null) {
                return Exposer.notExist(seckillId);
            } else {
                redisCacheDao.put("seckill", seckillId, seckill);
            }
        }
        Date startTime = seckill.getBegintime();
        Date endTime = seckill.getEndtime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return Exposer.timeError(seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMD5(seckillId);
        return Exposer.ok(md5, seckillId);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return seckillId + md5;
    }

    @Override
    @Transactional //开启事务
    public ExecutionResult executeSeckill(long seckllId, String md5) {
        if (md5 == null || !md5.equals(getMD5(seckllId))) {
            throw new SeckillException("seckill data rewirite");
        }

        Date nowTime = new Date();
        //执行秒杀逻辑=减库存+记录购买行为
        try {
            //记录购买行为
            SeckillResult killed = new SeckillResult();
            killed.setSeckid(seckllId);
            Integer userid = ShiroKit.getUser().getId();
            killed.setUserid(userid);
            killed.setState(Byte.parseByte("0"));
            int insertCount = seckillResultMapper.insert(killed);
            if (insertCount <= 0) {
                //重复秒杀
                throw new RepeatKillException("seckill repeated");
            } else {
                int updateCount = baseMapper.reduceNumber(seckllId, nowTime);
                if (updateCount <= 0) {
                    //没有更新到记录
                    throw new SeckillClosedException("seckill is closed");
                } else {
                    SeckillResult seckillResult = seckillResultMapper.queryByIdWithSeckill(seckllId, userid);
                    return new ExecutionResult(seckllId, SeckillStateEnum.SUCCESS, seckillResult);
                }
            }
        } catch (SeckillClosedException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            //所有编译器异常转换为运行期异常
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }

    @Override
    public ExecutionResult executeSeckillProcedure(long seckillid, String md5) {
        if (md5 == null || !md5.equals(getMD5(seckillid))) {
            return ExecutionResult.error(seckillid, SeckillStateEnum.DATA_REWRITE);
        }
        Date killTime = new Date();
        Map<String, Object> map = new HashMap<>();
        map.put("seckillId", seckillid);
        Integer userid = ShiroKit.getUser().getId();
        map.put("phone", userid);
        map.put("killTime", killTime);
        map.put("result", null);
        try {
            baseMapper.killByProcedure(map);
            int result = MapUtils.getInteger(map, "result", -2);
            if (result == 1) {
                SeckillResult seckillResult = seckillResultMapper.queryByIdWithSeckill(seckillid, userid);
                return ExecutionResult.ok(seckillid, SeckillStateEnum.SUCCESS, seckillResult);
            } else {
                return ExecutionResult.error(seckillid, SeckillStateEnum.stateOf(result));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ExecutionResult(seckillid, SeckillStateEnum.INNER_ERROR);
        }
    }
}
