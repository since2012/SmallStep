package org.tc.shiro.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_user_role")
public class SysUserRole {
    /**
     * 用户角色关联表 ID
     */
    @Id
    private Byte id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Byte userId;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Byte roleId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取用户角色关联表 ID
     *
     * @return id - 用户角色关联表 ID
     */
    public Byte getId() {
        return id;
    }

    /**
     * 设置用户角色关联表 ID
     *
     * @param id 用户角色关联表 ID
     */
    public void setId(Byte id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Byte getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Byte userId) {
        this.userId = userId;
    }

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Byte getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Byte roleId) {
        this.roleId = roleId;
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