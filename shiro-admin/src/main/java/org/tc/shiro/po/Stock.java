package org.tc.shiro.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class Stock {
    /**
     * 商品库存ID
     */
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * 商品名称
     */
    @NotEmpty
    private String name;

    /**
     * 原价
     */
    @NotNull
    private BigDecimal primeprice;

    /**
     * 秒杀价
     */
    @NotNull
    private BigDecimal saleprice;

    /**
     * 库存数量
     */
    @Min(10)
    private Integer total;

    /**
     * 秒杀开始时间
     */
    @Column(name = "begintime")
    @NotNull
    private Date begintime;

    /**
     * 秒杀结束时间
     */
    @Column(name = "endtime")
    @NotNull
    private Date endtime;

    /**
     * 创建时间
     */
    @Column(name = "createtime")
    private Date createtime;

}