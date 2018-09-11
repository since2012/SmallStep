package org.tc.shiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.mapper.LoginLogMapper;
import org.tc.shiro.po.LoginLog;
import org.tc.shiro.service.ILoginLogService;

import java.util.List;

/**
 * <p>
 * 登录记录 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
@Service
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLogMapper, LoginLog> implements ILoginLogService {

    @Override
    public void deleteAll() {
        baseMapper.deleteAll();
    }

    @Override
    public PageInfo<LoginLog> page(String logName, String message, String beginTime, String endTime,
                                   Integer pageNo, Integer pageSize, String sort) {
        PageHelper.startPage(pageNo, pageSize, sort);

//        Example example = new Example(Cmd.class);
//        Example.Criteria criteria = example.createCriteria();
//        //where条件
//        if (!StringUtils.isBlank(command.getName())) {
//            criteria.andLike("command", "%" + command.getName().trim() + "%");
//        }
//        if (!StringUtils.isBlank(command.getDescription())) {
//            criteria.andLike("command", "%" + command.getDescription().trim() + "%");
//        }
//        //排序依据
//        example.orderBy("id").asc();
//        List<Cmd> page = baseMapper.selectByExample(example);
        List<LoginLog> list = this.baseMapper.list(logName, message, beginTime, endTime);
        //用PageInfo对结果进行包装
        PageInfo<LoginLog> page = new PageInfo<LoginLog>(list);
        return page;
    }

}
