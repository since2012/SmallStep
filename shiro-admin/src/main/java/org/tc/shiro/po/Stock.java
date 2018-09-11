package org.tc.shiro.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
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
    private String name;

    /**
     * 库存数量
     */
    private Integer total;

    /**
     * 秒杀开始时间
     */
    @Column(name = "begintime")
    private Date begintime;

    /**
     * 秒杀结束时间
     */
    @Column(name = "endtime")
    private Date endtime;

    /**
     * 创建时间
     */
    @Column(name = "createtime")
    private Date createtime;

}