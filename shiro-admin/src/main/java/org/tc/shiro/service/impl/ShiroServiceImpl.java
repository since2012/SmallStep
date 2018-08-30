package org.tc.shiro.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.core.shiro.vo.ShiroUser;
import org.tc.shiro.mapper.SysResourceMapper;
import org.tc.shiro.mapper.SysRoleMapper;
import org.tc.shiro.mapper.SysUserMapper;
import org.tc.shiro.po.SysResource;
import org.tc.shiro.po.SysRole;
import org.tc.shiro.po.SysUser;
import org.tc.shiro.service.IShiroService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShiroServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements IShiroService {

    @Autowired
    private SysResourceMapper resourceMapper;
    @Autowired
    private SysRoleMapper roleMapper;

    @Override
    public SysUser getUserByAccount(String account) {
        if (StringUtils.isBlank(account)) {
            throw new UnknownAccountException("缺失用户名");
        }
        SysUser sysUser = new SysUser();
        sysUser.setUsername(account);
        try {
            sysUser = baseMapper.selectOne(sysUser);
        } catch (Exception e) {
            throw new UnknownAccountException("用户名或密码的错误");
        }
        // 账号不存在
        if (sysUser == null) {
            throw new UnknownAccountException("用户名或密码的错误");
        }
        // 账号被冻结
        if (!sysUser.getEnable()) {
            throw new LockedAccountException("用户已经被禁用，请联系管理员启用该账号");
        }
        return sysUser;
    }

    @Override
    public List<String> findPermissionsByRoleId(Byte roleid) {
        List<SysResource> sysResources = resourceMapper.selectByRid(roleid);
        ArrayList<String> list = new ArrayList<>();
        for (SysResource resource : sysResources) {
            list.add(resource.getUrl());
        }
        return list;
    }

    @Override
    public String findRoleNameByRoleId(Byte roleid) {
        SysRole sysRole = roleMapper.selectByPrimaryKey(roleid);
        return sysRole.getSn();
    }

    @Override
    public SimpleAuthenticationInfo getSimpleInfo(SysUser user, String realmName) {
        ShiroUser shiroUser = getShiroUser(user);
        String credentials = user.getPassword();
        String source = user.getUsername();
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
    private ShiroUser getShiroUser(SysUser user) {
        ShiroUser shiroUser = new ShiroUser();
        BeanUtils.copyProperties(user, shiroUser);

        List<SysRole> sysRoles = roleMapper.selectByUid(user.getId());
        List<Byte> roleList = new ArrayList<Byte>();
        List<String> roleNameList = new ArrayList<String>();
        for (SysRole role : sysRoles) {
            roleList.add(role.getId());
            roleNameList.add(role.getSn());
        }
        shiroUser.setRoleList(roleList);
        shiroUser.setRoleNames(roleNameList);

        return shiroUser;
    }
}
