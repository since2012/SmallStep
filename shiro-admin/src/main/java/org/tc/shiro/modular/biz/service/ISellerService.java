package org.tc.shiro.modular.biz.service;

import com.github.pagehelper.PageInfo;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.Seller;

import java.util.List;

/**
 * 商家Service
 *
 * @author TC
 * @Date 2018-07-29 16:43:59
 */
public interface ISellerService extends IBaseService<Seller> {

    /**
     * 新增
     *
     * @param seller
     */
    public void add(Seller seller);

    /**
     * 编辑
     *
     * @param seller
     */
    public void edit(Seller seller);

    /**
     * 删除（有优惠券数据不可删除）
     *
     * @param id
     */
    public void del(Integer id);

    /**
     * 切换状态
     *
     * @param id
     * @return
     */
    public int switchStatus(Integer id);

    /**
     * 分页查询
     *
     * @param pageSize
     * @return
     */
    PageInfo<Seller> page(Seller seller, Integer pageNo, Integer pageSize, String sort);

    /**
     * 超级管理员查询所有
     *
     * @return
     */
    public List<ZTreeNode> getSellerTree();
//
//    /**
//     * 普通管理员查看范围内数据
//     *
//     * @param userId
//     * @return
//     */
//    public List<ZTreeNode> getSellerTreeByScope(Integer userId);
}
