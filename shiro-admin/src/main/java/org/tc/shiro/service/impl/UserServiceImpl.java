package org.tc.shiro.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.core.shiro.kit.ShiroKit;
import org.tc.shiro.mapper.SysUserMapper;
import org.tc.shiro.mapper.SysUserRoleMapper;
import org.tc.shiro.po.SysUser;
import org.tc.shiro.service.IUserService;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements IUserService {


    @Autowired
    private SysUserRoleMapper userRoleMapper;

    public static final Byte ADMIN_ID = 1;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(SysUser sysUser, List<Byte> roleIdList) {
        setPwd(sysUser, sysUser.getPassword());
        sysUser.setCreateTime(new Date());
        int count = baseMapper.insertUseGeneratedKeys(sysUser);
        if (!retBool(count)) {
            return false;
        }
        count = userRoleMapper.insertBatch(sysUser.getId(), roleIdList);
        return retBool(count);
    }

    /**
     * 给用户配置新的密码和盐
     *
     * @param user
     * @param password 新密码
     * @return
     */
    private SysUser setPwd(SysUser user, String password) {
        String randomSalt = user.getUsername();
        String newMd5 = ShiroKit.md5(password, randomSalt);
        user.setPassword(newMd5);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertBatch(Byte userId, List<Byte> roleIdList) {
        int count = userRoleMapper.insertBatch(userId, roleIdList);
        return retBool(count);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByIdList(List<Byte> ids) {

        if (ids.contains(ADMIN_ID)) {
            throw new ShiroException("不能删除管理员用户");
        }
        //先删除子表数据
        int count = userRoleMapper.deleteByUids(ids);
        //主表后处理
        count += baseMapper.deleteByIdList(ids);
        return retBool(count);
    }

    @Override
    public boolean disableByIdList(List<Byte> ids) {

        if (ids.contains(ADMIN_ID)) {
            throw new ShiroException("不能禁用管理员用户");
        }
        int count = baseMapper.disableByIdList(ids);
        return retBool(count);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(SysUser user, List<Byte> rids) {
        Byte id = user.getId();
        boolean result = baseMapper.existsWithPrimaryKey(id);
        //假数据
        if (!result) {
            return false;
        }
        SysUser sysUser = baseMapper.selectByPrimaryKey(id);
        //变更则设为新值
        if (!sysUser.getPassword().equals(user.getPassword())) {
            setPwd(sysUser, sysUser.getPassword());
        } else {
            user.setPassword(null);
        }
        user.setEditTime(new Date());

        //先删除
        userRoleMapper.deleteByUid(id);
        // 后生成
        userRoleMapper.insertBatch(id, rids);

        int count = baseMapper.updateByPrimaryKeySelective(user);
        return retBool(count);
    }

}
