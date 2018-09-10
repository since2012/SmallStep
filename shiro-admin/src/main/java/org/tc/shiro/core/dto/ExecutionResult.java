package org.tc.shiro.core.dto;

import lombok.Data;
import org.tc.shiro.core.enums.SeckillStateEnum;
import org.tc.shiro.po.SeckillResult;

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
    private SeckillResult seckillResult;

    /**
     * 秒杀成功
     *
     * @param seckillId
     * @param seckillStateEnum
     * @param seckillResult
     */
    public ExecutionResult(long seckillId, SeckillStateEnum seckillStateEnum, SeckillResult seckillResult) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
        this.seckillResult = seckillResult;
    }

    /**
     * 秒杀失败
     *
     * @param seckillId
     * @param seckillStateEnum
     */
    public ExecutionResult(long seckillId, SeckillStateEnum seckillStateEnum) {
        this.seckillId = seckillId;
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
    }

    /**
     * 秒杀失败
     *
     * @param seckillStateEnum
     */
    public ExecutionResult(SeckillStateEnum seckillStateEnum) {
        this.state = seckillStateEnum.getState();
        this.stateInfo = seckillStateEnum.getStateInfo();
    }

    /**
     * 成功
     *
     * @param seckillId
     * @param seckillStateEnum
     * @param seckillResult
     * @return
     */
    public static ExecutionResult ok(long seckillId, SeckillStateEnum seckillStateEnum, SeckillResult seckillResult) {
        return new ExecutionResult(seckillId, seckillStateEnum, seckillResult);
    }

    /**
     * 失败
     *
     * @param seckillId
     * @param seckillStateEnum
     * @return
     */
    public static ExecutionResult error(long seckillId, SeckillStateEnum seckillStateEnum) {
        return new ExecutionResult(seckillId, seckillStateEnum);
    }

    /**
     * 失败
     *
     * @param seckillStateEnum
     * @return
     */
    public static ExecutionResult error(SeckillStateEnum seckillStateEnum) {
        return new ExecutionResult(seckillStateEnum);
    }
}
