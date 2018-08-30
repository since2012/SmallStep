package org.tc.shiro.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_role_resource")
public class SysRoleResource {
    /**
     * 角色资源关联 ID
     */
    @Id
    private Byte id;

    /**
     * 角色 id
     */
    @Column(name = "role_id")
    private Byte roleId;

    /**
     * 资源 id
     */
    @Column(name = "resource_id")
    private Byte resourceId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取角色资源关联 ID
     *
     * @return id - 角色资源关联 ID
     */
    public Byte getId() {
        return id;
    }

    /**
     * 设置角色资源关联 ID
     *
     * @param id 角色资源关联 ID
     */
    public void setId(Byte id) {
        this.id = id;
    }

    /**
     * 获取角色 id
     *
     * @return role_id - 角色 id
     */
    public Byte getRoleId() {
        return roleId;
    }

    /**
     * 设置角色 id
     *
     * @param roleId 角色 id
     */
    public void setRoleId(Byte roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取资源 id
     *
     * @return resource_id - 资源 id
     */
    public Byte getResourceId() {
        return resourceId;
    }

    /**
     * 设置资源 id
     *
     * @param resourceId 资源 id
     */
    public void setResourceId(Byte resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}