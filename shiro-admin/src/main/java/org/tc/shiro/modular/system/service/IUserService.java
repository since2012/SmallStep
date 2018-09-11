package org.tc.shiro.modular.system.service;

import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.User;

import java.util.List;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
public interface IUserService extends IBaseService<User> {


    public void add(User user);

    public boolean edit(User newUser, User oldUser);

    /**
     * 修改密码
     *
     * @param oldPwd
     * @param newPwd
     * @param rePwd
     */
    public void changePwd(String oldPwd, String newPwd, String rePwd);

    public void resetPwd(Integer id);

    /**
     * 修改用户状态
     */
    void setStatus(Integer userId, int status);

    /**
     * 设置用户的角色
     */
    void setRoles(Integer userId, String roleIds);

    /**
     * 根据条件查询用户列表
     */
    List<User> list(User user, String beginTime, String endTime);

}
