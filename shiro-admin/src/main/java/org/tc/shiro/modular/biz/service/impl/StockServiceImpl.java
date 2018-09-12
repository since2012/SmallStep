package org.tc.shiro.modular.biz.service.impl;

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
import org.tc.shiro.cache.RedisTemplateDao;
import org.tc.shiro.core.common.constant.cache.Cache;
import org.tc.shiro.core.common.constant.cache.CacheKey;
import org.tc.shiro.core.common.constant.enums.SeckillState;
import org.tc.shiro.core.common.exception.RepeatKillException;
import org.tc.shiro.core.common.exception.SeckillClosedException;
import org.tc.shiro.core.common.exception.SeckillException;
import org.tc.shiro.core.dto.ExecutionResult;
import org.tc.shiro.core.dto.Exposer;
import org.tc.shiro.core.shiroext.kit.ShiroKit;
import org.tc.shiro.mapper.SeckillMapper;
import org.tc.shiro.mapper.StockMapper;
import org.tc.shiro.modular.biz.service.IStockService;
import org.tc.shiro.po.Seckill;
import org.tc.shiro.po.Stock;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: TC
 * Description:
 */
@Slf4j
@Service
public class StockServiceImpl extends BaseServiceImpl<StockMapper, Stock> implements IStockService {

    public static final String salt = "test";

    @Autowired
    private SeckillMapper seckillMapper;
    @Autowired
    private RedisTemplateDao redisTemplateDao;

    @Override
    public PageInfo<Stock> page(Stock stock, Integer pageNo, Integer pageSize, String sort) {

        if (StringUtils.isBlank(stock.getName())) {
            stock.setName(null);
        }
        PageHelper.startPage(pageNo, pageSize, sort);
        List<Stock> list = baseMapper.list(stock);
        PageInfo<Stock> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public Exposer exoportSeckillUrl(long id) {
        String hKey = Cache.BIZ + ":" + CacheKey.STOCK;
        String hashKey = id + "";
        Stock stock = (Stock) redisTemplateDao.hashGet(hKey, hashKey);
        if (stock == null) {
            stock = baseMapper.selectByPrimaryKey(id);
            if (stock == null) {
                return Exposer.notExist(id);
            } else {
                redisTemplateDao.hashPush(hKey, hashKey, stock);
                redisTemplateDao.expire(hKey, 1000);
            }
        }
        Date startTime = stock.getBegintime();
        Date endTime = stock.getEndtime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return Exposer.timeError(id, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMD5(id);
        return Exposer.ok(md5, id);
    }

    private String getMD5(long id) {
        String base = id + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return id + md5;
    }

    @Override
    @Transactional //开启事务
    public ExecutionResult executeSeckill(long id, String md5) {
        if (md5 == null || !md5.equals(getMD5(id))) {
            throw new SeckillException("stock data rewirite");
        }

        Date nowTime = new Date();
        //执行秒杀逻辑=减库存+记录购买行为
        try {
            //记录购买行为
            Seckill killed = new Seckill();
            killed.setStockid(id);
            Integer userid = ShiroKit.getUser().getId();
            killed.setUserid(userid);
            killed.setState(Byte.parseByte("0"));
            int insertCount = seckillMapper.insert(killed);
            if (insertCount <= 0) {
                //重复秒杀
                throw new RepeatKillException("stock repeated");
            } else {
                int updateCount = baseMapper.reduceNumber(id, nowTime);
                if (updateCount <= 0) {
                    //没有更新到记录
                    throw new SeckillClosedException("stock is closed");
                } else {
                    Seckill seckill = seckillMapper.selectById(id, userid);
                    return new ExecutionResult(id, SeckillState.SUCCESS, seckill);
                }
            }
        } catch (SeckillClosedException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            //所有编译器异常转换为运行期异常
            throw new SeckillException("stock inner error:" + e.getMessage());
        }
    }

    @Override
    public ExecutionResult executeSeckillProcedure(long id, String md5) {
        if (md5 == null || !md5.equals(getMD5(id))) {
            return ExecutionResult.error(id, SeckillState.DATA_REWRITE);
        }
        Date killTime = new Date();
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        Integer userid = ShiroKit.getUser().getId();
        map.put("phone", userid);
        map.put("killTime", killTime);
        map.put("result", null);
        try {
            baseMapper.killByProcedure(map);
            int result = MapUtils.getInteger(map, "result", -2);
            if (result == 1) {
                Seckill seckill = seckillMapper.selectById(id, userid);
                return ExecutionResult.ok(id, SeckillState.SUCCESS, seckill);
            } else {
                return ExecutionResult.error(id, SeckillState.stateOf(result));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ExecutionResult(id, SeckillState.INNER_ERROR);
        }
    }
}
