package org.tc.shiro.mapper;


import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.Seckill;

public interface SeckillMapper extends MyMapper<Seckill> {

    /**
     * 根据id查询SuccessKilled并携带秒杀对象实体
     *
     * @param stockid
     * @return
     */
    Seckill selectById(@Param("stockid") Long stockid, @Param("userid") long userid);

    /**
     * 是否存在
     *
     * @param stockid
     * @return
     */
    boolean existsByStockid(@Param("stockid") Long stockid);

}
