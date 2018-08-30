package org.tc.shiro.service;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.SysUser;

import java.util.List;

/**
 * 定义shirorealm所需数据的接口
 *
 * @author fengshuonan
 * @date 2016年12月5日 上午10:23:34
 */
public interface IShiroService extends IBaseService<SysUser> {

    /**
     * 根据账号获取登录用户
     *
     * @param account 账号
     */
    SysUser getUserByAccount(String account);

    /**
     * 获取权限列表通过角色id
     *
     * @param roleid 角色id
     */
    List<String> findPermissionsByRoleId(Byte roleid);

    /**
     * 根据角色id获取角色名称
     *
     * @param roleid 角色id
     */
    String findRoleNameByRoleId(Byte roleid);

    /**
     * 获取shiro的认证信息
     */
    SimpleAuthenticationInfo getSimpleInfo(SysUser user, String realmName);

}
