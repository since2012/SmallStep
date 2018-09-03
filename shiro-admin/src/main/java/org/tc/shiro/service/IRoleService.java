package org.tc.shiro.service;

import com.stylefeng.guns.core.ztree.ZTreeNode;
import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.Role;

import java.util.List;

/**
 * 角色相关业务
 *
 * @author fengshuonan
 * @Date 2017年1月10日 下午9:11:57
 */
public interface IRoleService extends IBaseService<Role> {

    /**
     * 删除单个角色
     *
     * @param roleId
     */
    public void delRoleById(Integer roleId);

    /**
     * 设置某个角色的权限
     *
     * @param roleId 角色id
     * @param ids    权限的id
     * @date 2017年2月13日 下午8:26:53
     */
    void setAuthority(Integer roleId, String ids);

    /**
     * 根据条件查询列表
     *
     * @param name
     * @return
     */
    public List<Role> list(String name);

    /**
     * 所有角色
     *
     * @return
     * @date 2017年2月18日 上午10:32:04
     */
    List<ZTreeNode> getRoleTree();

    /**
     * 已配置情况
     *
     * @return
     * @date 2017年2月18日 上午10:32:04
     */
    List<ZTreeNode> getCheckedRoleTree(Integer userId);
}
