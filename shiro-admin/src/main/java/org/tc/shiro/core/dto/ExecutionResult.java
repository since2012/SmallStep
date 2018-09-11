package org.tc.shiro.core.dto;

import lombok.Data;
import org.tc.shiro.core.common.constant.enums.SeckillState;
import org.tc.shiro.po.Seckill;

/**
 * 秒杀执行结果
 */
@Data
public class ExecutionResult {

    //秒杀商品ID
    private long seckillId;

    //秒杀执行结果状态
    private int state;

    //状态标识
    private String stateInfo;

    //秒杀成功信息
    private Seckill seckill;

    /**
     * 秒杀成功
     *
     * @param seckillId
     * @param seckillState
     * @param seckill
     */
    public ExecutionResult(long seckillId, SeckillState seckillState, Seckill seckill) {
        this.seckillId = seckillId;
        this.state = seckillState.getState();
        this.stateInfo = seckillState.getStateInfo();
        this.seckill = seckill;
    }

    /**
     * 秒杀失败
     *
     * @param seckillId
     * @param seckillState
     */
    public ExecutionResult(long seckillId, SeckillState seckillState) {
        this.seckillId = seckillId;
        this.state = seckillState.getState();
        this.stateInfo = seckillState.getStateInfo();
    }

    /**
     * 秒杀失败
     *
     * @param seckillState
     */
    public ExecutionResult(SeckillState seckillState) {
        this.state = seckillState.getState();
        this.stateInfo = seckillState.getStateInfo();
    }

    /**
     * 成功
     *
     * @param seckillId
     * @param seckillState
     * @param seckill
     * @return
     */
    public static ExecutionResult ok(long seckillId, SeckillState seckillState, Seckill seckill) {
        return new ExecutionResult(seckillId, seckillState, seckill);
    }

    /**
     * 失败
     *
     * @param seckillId
     * @param seckillState
     * @return
     */
    public static ExecutionResult error(long seckillId, SeckillState seckillState) {
        return new ExecutionResult(seckillId, seckillState);
    }

    /**
     * 失败
     *
     * @param seckillState
     * @return
     */
    public static ExecutionResult error(SeckillState seckillState) {
        return new ExecutionResult(seckillState);
    }
}
