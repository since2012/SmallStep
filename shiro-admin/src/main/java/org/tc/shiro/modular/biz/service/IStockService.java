package org.tc.shiro.modular.biz.service;

import com.github.pagehelper.PageInfo;
import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.core.dto.Exposer;
import org.tc.shiro.core.dto.SeckillExecutionResult;
import org.tc.shiro.po.Stock;

public interface IStockService extends IBaseService<Stock> {

    /**
     * 新增
     * @param stock
     */
    public void add(Stock stock);

    /**
     * 修改
     * @param stock
     */
    public void edit(Stock stock);

    /**
     * 删除
     * @param id
     */
    public void del(Long id);

    /**
     * 分页查询
     *
     * @param pageSize
     * @return
     */
    PageInfo<Stock> page(Stock stock, Integer pageNo, Integer pageSize, String sort);

    /**
     * 暴露接口
     *
     * @param id
     * @return
     */
    Exposer exoportSeckillUrl(long id);

    /**
     * 秒杀
     *
     * @param id
     * @param md5
     * @return
     */
    SeckillExecutionResult executeSeckill(long id, String md5);

    /**
     * 秒杀（存储过程）
     *
     * @param id
     * @param md5
     * @return
     */
    SeckillExecutionResult executeSeckillProcedure(long id, String md5);

}
