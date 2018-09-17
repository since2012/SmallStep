package org.tc.shiro.core.dto;

import lombok.Data;
import org.tc.shiro.core.common.constant.enums.SeckillState;
import org.tc.shiro.po.Seckill;

/**
 * 秒杀执行结果
 */
@Data
public class SeckillExecutionResult {

    //秒杀商品ID
    private long seckillId;
    //状态标识
    private String message;
    //秒杀成功信息
    private Seckill seckill;

    /**
     * 秒杀成功
     *
     * @param seckillId
     * @param seckillState
     * @param seckill
     */
    public SeckillExecutionResult(long seckillId, SeckillState seckillState, Seckill seckill) {
        this.seckillId = seckillId;
        this.message = seckillState.getMessage();
        this.seckill = seckill;
    }

    /**
     * 秒杀失败
     *
     * @param seckillId
     * @param seckillState
     */
    public SeckillExecutionResult(long seckillId, SeckillState seckillState) {
        this.seckillId = seckillId;
        this.message = seckillState.getMessage();
    }

    /**
     * 秒杀失败
     *
     * @param seckillState
     */
    public SeckillExecutionResult(SeckillState seckillState) {
        this.message = seckillState.getMessage();
    }

    /**
     * 成功
     *
     * @param seckillId
     * @param seckillState
     * @param seckill
     * @return
     */
    public static SeckillExecutionResult ok(long seckillId, SeckillState seckillState, Seckill seckill) {
        return new SeckillExecutionResult(seckillId, seckillState, seckill);
    }

    /**
     * 失败
     *
     * @param seckillId
     * @param seckillState
     * @return
     */
    public static SeckillExecutionResult error(long seckillId, SeckillState seckillState) {
        return new SeckillExecutionResult(seckillId, seckillState);
    }

    /**
     * 失败
     *
     * @param seckillState
     * @return
     */
    public static SeckillExecutionResult error(SeckillState seckillState) {
        return new SeckillExecutionResult(seckillState);
    }
}
