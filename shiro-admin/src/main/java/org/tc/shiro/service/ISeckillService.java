package org.tc.shiro.service;

import com.github.pagehelper.PageInfo;
import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.core.dto.ExecutionResult;
import org.tc.shiro.core.dto.Exposer;
import org.tc.shiro.po.Seckill;

public interface ISeckillService extends IBaseService<Seckill> {

    /**
     * 分页查询
     *
     * @param pageSize
     * @return
     */
    PageInfo<Seckill> page(Seckill seckill, Integer pageNo, Integer pageSize, String sort);

    /**
     * 暴露接口
     *
     * @param seckillId
     * @return
     */
    Exposer exoportSeckillUrl(long seckillId);

    /**
     * 秒杀
     *
     * @param seckllId
     * @param md5
     * @return
     */
    ExecutionResult executeSeckill(long seckllId, String md5);

    /**
     * 秒杀（存储过程）
     *
     * @param seckillid
     * @param md5
     * @return
     */
    ExecutionResult executeSeckillProcedure(long seckillid, String md5);

}
