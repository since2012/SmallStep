package org.tc.shiro.po;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Coupon {
    /**
     * 优惠券ID
     */
    @Id
    private String id;

    /**
     * 卖家ID
     */
    @NotNull
    private Integer sellerid;

    /**
     * 起始日期
     */
    @NotNull
    @JSONField(format = "yyyy-MM-dd")
    private Date beginday;

    /**
     * 结束日期
     */
    @NotNull
    @Future
    @JSONField(format = "yyyy-MM-dd")
    private Date endday;

    /**
     * 领取时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm")
    private Date receptday;

    /**
     * 领取人的电话号码
     */
    private String usertel;

    /**
     * 使用验证码
     */
    private String code;

    /**
     * 1:已使用，0：未使用
     */
    private Integer valid;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 获取优惠券ID
     *
     * @return id - 优惠券ID
     */
    public String getId() {
        return id;
    }

    /**
     * 设置优惠券ID
     *
     * @param id 优惠券ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取卖家ID
     *
     * @return sellerid - 卖家ID
     */
    public Integer getSellerid() {
        return sellerid;
    }

    /**
     * 设置卖家ID
     *
     * @param sellerid 卖家ID
     */
    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    /**
     * 获取起始日期
     *
     * @return beginday - 起始日期
     */
    public Date getBeginday() {
        return beginday;
    }

    /**
     * 设置起始日期
     *
     * @param beginday 起始日期
     */
    public void setBeginday(Date beginday) {
        this.beginday = beginday;
    }

    /**
     * 获取结束日期
     *
     * @return endday - 结束日期
     */
    public Date getEndday() {
        return endday;
    }

    /**
     * 设置结束日期
     *
     * @param endday 结束日期
     */
    public void setEndday(Date endday) {
        this.endday = endday;
    }

    /**
     * 获取领取时间
     *
     * @return receptday - 领取时间
     */
    public Date getReceptday() {
        return receptday;
    }

    /**
     * 设置领取时间
     *
     * @param receptday 领取时间
     */
    public void setReceptday(Date receptday) {
        this.receptday = receptday;
    }

    /**
     * 获取领取人的电话号码
     *
     * @return usertel - 领取人的电话号码
     */
    public String getUsertel() {
        return usertel;
    }

    /**
     * 设置领取人的电话号码
     *
     * @param usertel 领取人的电话号码
     */
    public void setUsertel(String usertel) {
        this.usertel = usertel;
    }

    /**
     * 获取使用验证码
     *
     * @return code - 使用验证码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置使用验证码
     *
     * @param code 使用验证码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取1:已使用，0：未使用
     *
     * @return valid - 1:已使用，0：未使用
     */
    public Integer getValid() {
        return valid;
    }

    /**
     * 设置1:已使用，0：未使用
     *
     * @param valid 1:已使用，0：未使用
     */
    public void setValid(Integer valid) {
        this.valid = valid;
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