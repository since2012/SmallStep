package org.tc.shiro.mapper;


import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.SeckillResult;

public interface SeckillResultMapper extends MyMapper<SeckillResult> {

    /**
     * 根据id查询SuccessKilled并携带秒杀对象实体
     *
     * @param seckid
     * @return
     */
    SeckillResult queryByIdWithSeckill(@Param("seckid") long seckid, @Param("userid") long userid);

}
