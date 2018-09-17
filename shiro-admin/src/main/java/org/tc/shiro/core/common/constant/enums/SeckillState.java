package org.tc.shiro.core.common.constant.enums;

/**
 * Author: Redinw
 * Description:
 */
public enum SeckillState {
    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀结束"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2, "系统异常"),
    DATA_REWRITE(-3, "数据篡改");

    private int code;
    private String message;

    SeckillState(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static SeckillState codeOf(int code) {
        for (SeckillState seckillState : values()) {
            if (seckillState.getCode() == code) {
                return seckillState;
            }
        }
        return null;
    }
}
