package org.tc.shiro.po;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(name = "sys_role")
public class SysRole {
    /**
     * 角色表 ID
     */
    @Id
    private Byte id;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String name;

    /**
     * 角色字符串
     */
    @NotBlank(message = "角色字符串不能为空")
    private String sn;

    /**
     * 假删：1启用，0禁用，NULL未启用
     */
    @NotNull(message = "状态信息缺失")
    private Boolean enable;

    /**
     * 上级ID
     */
    @Column(name = "top_id")
    private Byte topId;

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
     * 排序码(升序排列)
     */
    @Column(name = "sort_code")
    private Float sortCode;

    /**
     * 获取角色表 ID
     *
     * @return id - 角色表 ID
     */
    public Byte getId() {
        return id;
    }

    /**
     * 设置角色表 ID
     *
     * @param id 角色表 ID
     */
    public void setId(Byte id) {
        this.id = id;
    }

    /**
     * 获取角色名称
     *
     * @return name - 角色名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置角色名称
     *
     * @param name 角色名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取角色字符串
     *
     * @return sn - 角色字符串
     */
    public String getSn() {
        return sn;
    }

    /**
     * 设置角色字符串
     *
     * @param sn 角色字符串
     */
    public void setSn(String sn) {
        this.sn = sn;
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
     * 获取上级ID
     *
     * @return top_id - 上级ID
     */
    public Byte getTopId() {
        return topId;
    }

    /**
     * 设置上级ID
     *
     * @param topId 上级ID
     */
    public void setTopId(Byte topId) {
        this.topId = topId;
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

    /**
     * 获取排序码(升序排列)
     *
     * @return sort_code - 排序码(升序排列)
     */
    public Float getSortCode() {
        return sortCode;
    }

    /**
     * 设置排序码(升序排列)
     *
     * @param sortCode 排序码(升序排列)
     */
    public void setSortCode(Float sortCode) {
        this.sortCode = sortCode;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SysRole{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", sn='").append(sn).append('\'');
        sb.append(", enable=").append(enable);
        sb.append(", topId=").append(topId);
        sb.append(", createTime=").append(createTime);
        sb.append(", editTime=").append(editTime);
        sb.append(", sortCode=").append(sortCode);
        sb.append('}');
        return sb.toString();
    }
}