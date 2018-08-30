package org.tc.shiro.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(name = "sys_user")
public class SysUser {
    /**
     * 用户 ID
     */
    @Id
    private Byte id;

    /**
     * 用户名（唯一）
     */
    @NotBlank(message = "用户名必填")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码必填")
    private String password;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称必填")
    private String nickname;

    /**
     * 部门ID
     */
    @Column(name = "dept_id")
    private Byte deptId;

    /**
     * 用户类型：1普通用户，2商户,可扩展
     */
    private Byte type;

    /**
     * 假删：1启用，0禁用，NULL未启用
     */
    @NotNull(message = "状态必填")
    private Boolean enable;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最近编辑时间（包括假删）
     */
    @Column(name = "edit_time")
    private Date editTime;

    /**
     * 获取用户 ID
     *
     * @return id - 用户 ID
     */
    public Byte getId() {
        return id;
    }

    /**
     * 设置用户 ID
     *
     * @param id 用户 ID
     */
    public void setId(Byte id) {
        this.id = id;
    }

    /**
     * 获取用户名（唯一）
     *
     * @return username - 用户名（唯一）
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名（唯一）
     *
     * @param username 用户名（唯一）
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取部门ID
     *
     * @return dept_id - 部门ID
     */
    public Byte getDeptId() {
        return deptId;
    }

    /**
     * 设置部门ID
     *
     * @param deptId 部门ID
     */
    public void setDeptId(Byte deptId) {
        this.deptId = deptId;
    }

    /**
     * 获取用户类型：1普通用户，2商户,可扩展
     *
     * @return type - 用户类型：1普通用户，2商户,可扩展
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置用户类型：1普通用户，2商户,可扩展
     *
     * @param type 用户类型：1普通用户，2商户,可扩展
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * 获取假删：1启用，0禁用，NULL未启用
     *
     * @return enable - 假删：1启用，0禁用，NULL未启用
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * 设置假删：1启用，0禁用，NULL未启用
     *
     * @param enable 假删：1启用，0禁用，NULL未启用
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
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

    /**
     * 获取最近编辑时间（包括假删）
     *
     * @return edit_time - 最近编辑时间（包括假删）
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * 设置最近编辑时间（包括假删）
     *
     * @param editTime 最近编辑时间（包括假删）
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }
}