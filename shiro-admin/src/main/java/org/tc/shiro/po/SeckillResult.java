package org.tc.shiro.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "seckill_result")
public class SeckillResult {
    /**
     * 秒杀商品ID
     */
    @Id
    @Column(name = "seckid")
    private Long seckid;

    /**
     * 用户手机号
     */
    @Id
    @Column(name = "userid")
    private Integer userid;

    /**
     * 状态标识:-1:无效； 0:成功 ；1:已付款； 2:已发货
     */
    private Byte state;

    /**
     * 创建时间
     */
    @Column(name = "createtime")
    private Date createtime;

    private Seckill seckill;
}