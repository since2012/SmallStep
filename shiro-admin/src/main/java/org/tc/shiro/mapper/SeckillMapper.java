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
    Seckill selectById(@Param("stockid") long stockid, @Param("userid") long userid);

}
