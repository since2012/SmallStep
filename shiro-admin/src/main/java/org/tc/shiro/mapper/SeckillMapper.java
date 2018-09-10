package org.tc.shiro.mapper;

import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.Seckill;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Author: Redinw
 * Description:
 */
public interface SeckillMapper extends MyMapper<Seckill> {

    /**
     * 查询列表
     *
     * @param seckill
     * @return
     */
    public List<Seckill> list(Seckill seckill);

    /**
     * 减库存
     *
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("id") long seckillId, @Param("killTime") Date killTime);

    /**
     * 使用存储过程执行秒杀
     *
     * @param paramsMap
     */
    void killByProcedure(Map<String, Object> paramsMap);
}
