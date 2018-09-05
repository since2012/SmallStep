package org.tc.shiro.mapper;

import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.LoginLog;

import java.util.List;

/**
 * <p>
 * 登录记录 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
public interface LoginLogMapper extends MyMapper<LoginLog> {

    /**
     * 清空所有数据
     */
    public void deleteAll();

    /**
     * 获取登录日志
     */
    List<LoginLog> list(@Param("logName") String logName, @Param("message") String message,
                        @Param("beginTime") String beginTime, @Param("endTime") String endTime);

}