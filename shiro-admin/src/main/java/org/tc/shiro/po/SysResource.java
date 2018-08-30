package org.tc.shiro.po;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Table(name = "sys_resource")
public class SysResource {
    /**
     * 资源 ID
     */
    @Id
    private Byte id;

    /**
     * 资源名称,一般是中文名称(给人看的)
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 资源权限字符串,一般是 Shiro 默认的通配符表示(给人看的)
     */
    @NotBlank(message = "权限字符串不能为空")
    private String permission;

    /**
     * 资源 url 表示,我们设计的系统让 Shiro 通过这个路径字符串去匹配浏览器中显示的路径
     */
    @NotBlank(message = "资源URL不能为空")
    private String url;

    /**
     * 假删：1启用，0禁用，NULL未启用
     */
    @NotNull(message = "是否启用未配置")
    private Boolean enable;

    /**
     * 上级ID
     */
    @Column(name = "top_id")
    private Byte topId;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最近编辑时间（包括假删）
     */
    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "edit_time")
    private Date editTime;

    /**
     * 排序码(升序排列)
     */
    @Column(name = "sort_code")
    private Float sortCode;

    /**
     * 获取资源 ID
     *
     * @return id - 资源 ID
     */
    public Byte getId() {
        return id;
    }

    /**
     * 设置资源 ID
     *
     * @param id 资源 ID
     */
    public void setId(Byte id) {
        this.id = id;
    }

    /**
     * 获取资源名称,一般是中文名称(给人看的)
     *
     * @return name - 资源名称,一般是中文名称(给人看的)
     */
    public String getName() {
        return name;
    }

    /**
     * 设置资源名称,一般是中文名称(给人看的)
     *
     * @param name 资源名称,一般是中文名称(给人看的)
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取资源权限字符串,一般是 Shiro 默认的通配符表示(给人看的)
     *
     * @return permission - 资源权限字符串,一般是 Shiro 默认的通配符表示(给人看的)
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 设置资源权限字符串,一般是 Shiro 默认的通配符表示(给人看的)
     *
     * @param permission 资源权限字符串,一般是 Shiro 默认的通配符表示(给人看的)
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * 获取资源 url 表示,我们设计的系统让 Shiro 通过这个路径字符串去匹配浏览器中显示的路径
     *
     * @return url - 资源 url 表示,我们设计的系统让 Shiro 通过这个路径字符串去匹配浏览器中显示的路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置资源 url 表示,我们设计的系统让 Shiro 通过这个路径字符串去匹配浏览器中显示的路径
     *
     * @param url 资源 url 表示,我们设计的系统让 Shiro 通过这个路径字符串去匹配浏览器中显示的路径
     */
    public void setUrl(String url) {
        this.url = url;
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
        final StringBuffer sb = new StringBuffer("SysResource{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", permission='").append(permission).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", enable=").append(enable);
        sb.append(", topId=").append(topId);
        sb.append(", createTime=").append(createTime);
        sb.append(", editTime=").append(editTime);
        sb.append(", sortCode=").append(sortCode);
        sb.append('}');
        return sb.toString();
    }
}