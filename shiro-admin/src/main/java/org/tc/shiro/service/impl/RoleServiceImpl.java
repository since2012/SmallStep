package org.tc.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.mapper.SysRoleMapper;
import org.tc.shiro.mapper.SysRoleResourceMapper;
import org.tc.shiro.mapper.SysUserRoleMapper;
import org.tc.shiro.po.SysRole;
import org.tc.shiro.po.SysRoleResource;
import org.tc.shiro.service.IRoleService;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liwei on 2016/9/18.
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<SysRoleMapper, SysRole> implements IRoleService {

    @Autowired
    private SysRoleResourceMapper roleResourceMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteBatchByIds(List<Byte> ids) {
        userRoleMapper.deleteByRids(ids);
        roleResourceMapper.deleteByRids(ids);
        baseMapper.deleteByIdList(ids);
        return true;
    }

    @Override
    public boolean disableBatchByIds(List<Byte> ids) {
        return retBool(baseMapper.diableByIdList(ids));
    }

    @Override
    public boolean delete(SysRoleResource sysRoleResource) {
        Example example = new Example(SysRoleResource.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roleId", sysRoleResource.getRoleId());
        criteria.andEqualTo("resourceId", sysRoleResource.getResourceId());
        int count = roleResourceMapper.deleteByExample(example);
        return retBool(count);
    }

    @Override
    public boolean updateResource(Byte roleId, List<Byte> resIds) {
        boolean result = baseMapper.existsWithPrimaryKey(roleId);
        if (!result) {
            return result;
        }
        //清空
        int count = roleResourceMapper.deleteByRid(roleId);
        //重建
        count += roleResourceMapper.insertBatch(roleId, resIds);
        return retBool(count);
    }

    @Override
    public List<Byte> selectRoleByUid(Byte uid) {
        List<SysRole> roles = baseMapper.selectByUid(uid);
        List<Byte> rids = new ArrayList<Byte>();
        for (SysRole r : roles) {
            rids.add(r.getId());
        }
        return rids;
    }

    @Override
    public List<Byte> selectResIdsByRid(Byte rid) {
        List<Byte> list = roleResourceMapper.selectByRid(rid);
        return list;
    }
}
