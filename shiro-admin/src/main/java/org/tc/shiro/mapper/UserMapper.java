package org.tc.shiro.mapper;

import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.User;

import java.util.List;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
public interface UserMapper extends MyMapper<User> {

    /**
     * 修改用户状态
     */
    int setStatus(@Param("userId") Integer userId, @Param("status") int status);

    /**
     * 修改密码
     */
    int changePwd(@Param("userId") Integer userId, @Param("pwd") String pwd);

    /**
     * 设置用户的角色
     */
    int setRoles(@Param("userId") Integer userId, @Param("roleIds") String roleIds);

    /**
     * 根据条件查询用户列表
     */
    List<User> list(@Param("dataScope") List<Integer> dataScope, @Param("user") User user,
                    @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 是否存在该账户号（忽略大小写）
     *
     * @param account
     * @return
     */
    public int countByAcount(@Param("account") String account);

    /**
     * 通过账号查找
     *
     * @param account
     * @return
     */
    public User selectByAccount(@Param("account") String account);

    /**
     * 通过角色ID查找
     *
     * @param roleid
     * @return
     */
    public List<User> selectByRoleid(@Param("roleid") String roleid);
}