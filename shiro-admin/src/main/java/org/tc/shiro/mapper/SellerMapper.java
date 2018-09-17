package org.tc.shiro.mapper;

import com.stylefeng.guns.core.ztree.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.Seller;

import java.util.List;

public interface SellerMapper extends MyMapper<Seller> {


    /**
     * 是否存在同名卖家
     *
     * @param name
     * @return
     */
    public boolean existsByName(@Param("name") String name);

    /**
     * 更新状态
     *
     * @param id
     * @return
     */
    public int switchStatus(@Param("id") Integer id);

    /**
     * 按条件查询
     *
     * @param seller
     * @return
     */
    public List<Seller> list(Seller seller);

    /**
     * 获取所有商家
     *
     * @return
     */
    public List<ZTreeNode> getSellerTree();

    /**
     * 获取范围内商家
     *
     * @param userid
     * @return
     */
//    public List<Map<String, Object>> getSellerTreeByScope(@Param("userid") Integer userid);
}