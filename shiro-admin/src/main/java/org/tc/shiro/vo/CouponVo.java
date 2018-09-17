package org.tc.shiro.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.Id;
import java.util.Date;

@Data
public class CouponVo {
    /**
     * 优惠券ID
     */
    @Id
    private String id;

    /**
     * 卖家ID
     */
    private Integer sellerid;

    /**
     * 起始日期
     */
    @JSONField(format = "yyyy-MM-dd")
    private Date beginday;

    /**
     * 结束日期
     */
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

    private String status;
    private String bgclass;
    private String sellername;
}
