package org.tc.shiro.modular.system.service;

import com.github.pagehelper.PageInfo;
import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.LoginLog;

/**
 * <p>
 * 登录记录 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
public interface ILoginLogService extends IBaseService<LoginLog> {

    /**
     * 清空所有数据
     */
    public void deleteAll();

    /**
     * 分页查询
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<LoginLog> page(String logName, String message, String beginTime, String endTime,
                            Integer pageNo, Integer pageSize, String sort);

}
