package org.tc.shiro.po;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Seller {
    /**
     * 商家ID
     */
    @Id
    private Integer id;

    /**
     * 商家名称
     */
    @NotEmpty
    private String name;

    /**
     * 商家地址
     */
    private String addr;

    /**
     * 状态：1 启用,0 禁用
     */
    @NotNull
    private Integer status;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 获取商家ID
     *
     * @return id - 商家ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置商家ID
     *
     * @param id 商家ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取商家名称
     *
     * @return name - 商家名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商家名称
     *
     * @param name 商家名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取商家地址
     *
     * @return addr - 商家地址
     */
    public String getAddr() {
        return addr;
    }

    /**
     * 设置商家地址
     *
     * @param addr 商家地址
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * 获取状态：1 启用,0 禁用
     *
     * @return status - 状态：1 启用,0 禁用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态：1 启用,0 禁用
     *
     * @param status 状态：1 启用,0 禁用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取创建时间
     *
     * @return createtime - 创建时间
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}