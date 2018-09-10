package org.tc.shiro.core.dto;

import lombok.Data;

/**
 * 暴露接口
 */
@Data
public class SeckillTimeData {

    //当前时间
    private long nowTime;
    //开启时间
    private long startTime;
    //结束时间
    private long endTime;

    /**
     * 未开始或已经结束
     *
     * @param nowTime
     * @param startTime
     * @param endTime
     */
    public SeckillTimeData( long nowTime, long startTime, long endTime) {
        this.nowTime = nowTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
