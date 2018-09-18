package org.tc.shiro.modular.system.service.impl;

import com.stylefeng.guns.core.util.Convert;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.core.ztree.MenuNode;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tc.shiro.core.common.constant.AdminConst;
import org.tc.shiro.core.common.constant.enums.TrebleStatus;
import org.tc.shiro.core.common.constant.factory.ConstantFactory;
import org.tc.shiro.core.shiroext.vo.ShiroUser;
import org.tc.shiro.mapper.MenuMapper;
import org.tc.shiro.mapper.UserMapper;
import org.tc.shiro.modular.system.service.IShiroService;
import org.tc.shiro.po.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShiroServiceImpl implements IShiroService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public User getByAccount(String account) {
        User user = userMapper.selectByAccount(account);
        // 账号不存在
        if (ToolUtil.isEmpty(user)) {
            throw new UnknownAccountException("用户名或密码的错误");
        }
        // 账号被冻结
        if (user.getStatus() != TrebleStatus.ACTIVED.getCode()) {
            throw new LockedAccountException("用户已经被禁用，请联系管理员启用该账号");
        }
        return user;
    }

    @Override
    public List<String> getAPIByRoleId(Integer roleid) {
        if (AdminConst.ADMIN_ID.equals(roleid)) {
            return menuMapper.getAPIOfAdmin();
        }
        return menuMapper.getAPIByRoleId(roleid);
    }

    @Override
    public String findRoleNameByRoleId(Integer roleid) {
        return ConstantFactory.me().getRoleEnName(roleid);
    }

    @Override
    public SimpleAuthenticationInfo getSimpleInfo(User user, String realmName) {
        ShiroUser shiroUser = toShiroUser(user);
        String credentials = user.getPassword();
        String source = user.getSalt();
        ByteSource credentialsSalt = new Md5Hash(source);
        //均为用户的正确信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(shiroUser, credentials, realmName);
        info.setCredentialsSalt(credentialsSalt);
        return info;
    }

    /**
     * 包装user
     *
     * @param user
     * @return
     */
    private ShiroUser toShiroUser(User user) {
        ShiroUser shiroUser = new ShiroUser();
        BeanUtils.copyProperties(user, shiroUser);
        shiroUser.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));

        Integer[] roleArray = Convert.toIntArray(user.getRoleid());
        List<Integer> roleList = new ArrayList<Integer>();
        List<String> roleNameList = new ArrayList<String>();
        for (Integer roleId : roleArray) {
            roleList.add(roleId);
            roleNameList.add(ConstantFactory.me().getSingleRoleName(roleId));
        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);
        List<MenuNode> menus = null;
        if (roleList.contains(AdminConst.ADMIN_ID)) {
            menus = menuMapper.getListByRoleIds(roleList, false);
        } else {
            menus = menuMapper.getListByRoleIds(roleList, true);
        }
        List<MenuNode> titles = MenuNode.buildTitle(menus);
//        titles = ApiMenuUtils.build(titles);
        shiroUser.setMenuList(titles);

        return shiroUser;
    }
}
