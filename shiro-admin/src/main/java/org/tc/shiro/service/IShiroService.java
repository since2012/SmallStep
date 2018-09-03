package org.tc.shiro.service;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.tc.shiro.po.User;

import java.util.List;

/**
 * 定义shirorealm所需数据的接口
 *
 * @author fengshuonan
 * @date 2016年12月5日 上午10:23:34
 */
public interface IShiroService {

    /**
     * 根据账号获取登录用户
     *
     * @param account 账号
     */
    User getUserByAccount(String account);

    /**
     * 获取权限列表通过角色id
     *
     * @param roleid 角色id
     */
    List<String> getAPIByRoleId(Integer roleid);

    /**
     * 根据角色id获取角色名称
     *
     * @param roleid 角色id
     */
    String findRoleNameByRoleId(Integer roleid);

    /**
     * 获取shiro的认证信息
     */
    SimpleAuthenticationInfo getSimpleInfo(User user, String realmName);

}
