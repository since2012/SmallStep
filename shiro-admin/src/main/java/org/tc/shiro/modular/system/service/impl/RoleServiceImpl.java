package org.tc.shiro.modular.system.service.impl;

import com.stylefeng.guns.core.support.CollectionKit;
import com.stylefeng.guns.core.support.StrKit;
import com.stylefeng.guns.core.util.Convert;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tc.mybatis.exception.GunsException;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.core.common.constant.AdminConst;
import org.tc.shiro.core.common.exception.BizExceptionEnum;
import org.tc.shiro.mapper.RelationMapper;
import org.tc.shiro.mapper.RoleMapper;
import org.tc.shiro.mapper.UserMapper;
import org.tc.shiro.modular.system.service.IRoleService;
import org.tc.shiro.po.Relation;
import org.tc.shiro.po.Role;
import org.tc.shiro.po.User;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RelationMapper relationMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delRoleById(Integer roleId) {
        //不能删除超级管理员角色
        if (roleId.equals(AdminConst.ADMIN_ROLE_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_DELETE_ADMIN);
        }
        //step1:处理用户
        List<User> list = userMapper.selectByRoleid("%" + roleId + "%");
        for (User user : list) {
            String roleid = user.getRoleid();
            if (StringUtils.isBlank(roleid)) {
                List<String> split = StrKit.split(roleid, ',');
                if (split.contains(roleId)) {
                    split.remove(roleId);
                    user.setRoleid(CollectionKit.join(split, ","));
                    userMapper.updateByPrimaryKey(user);
                }
            }
        }
        //step2:删除该角色所有的权限
        this.relationMapper.deleteByRoleId(roleId);
        //step3:删除角色
        this.baseMapper.deleteByPrimaryKey(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setAuthority(Integer roleId, String ids) {
        // 删除该角色所有的权限
        this.relationMapper.deleteByRoleId(roleId);
        // 添加新的权限
        for (Long id : Convert.toLongArray(true, Convert.toStrArray(",", ids))) {
            Relation relation = new Relation();
            relation.setRoleid(roleId);
            relation.setMenuid(id);
            this.relationMapper.insertUseGeneratedKeys(relation);
        }
    }

    @Override
    public List<Role> list(String name) {
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            criteria.andLike("name", "%" + name + "%");
        }
        example.setOrderByClause("id asc");
        List<Role> list = baseMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<ZTreeNode> getRoleTree() {
        List<ZTreeNode> list = this.baseMapper.roleTree();
        return list;
    }

    @Override
    public List<ZTreeNode> getCheckedRoleTree(Integer userId) {
        User user = this.userMapper.selectByPrimaryKey(userId);
        String roleid = user.getRoleid();
        List<ZTreeNode> list;
        if (ToolUtil.isEmpty(roleid)) {
            list = this.baseMapper.roleTree();
        } else {
            List<String> split = StrKit.split(roleid, ',');
            list = this.baseMapper.checkedRoleTree(split);
        }
        return list;
    }
}
