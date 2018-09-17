package org.tc.shiro.modular.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tc.mybatis.exception.GunsException;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.core.common.exception.BizExceptionEnum;
import org.tc.shiro.mapper.CouponMapper;
import org.tc.shiro.mapper.SellerMapper;
import org.tc.shiro.modular.biz.service.ISellerService;
import org.tc.shiro.po.Seller;

import java.util.Date;
import java.util.List;

/**
 * 商家Service实现
 *
 * @author TC
 * @Date 2018-07-29 16:43:59
 */
@Service
public class SellerServiceImpl extends BaseServiceImpl<SellerMapper, Seller> implements ISellerService {

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public void add(Seller seller) {
        boolean exists = baseMapper.existsByName(seller.getName());
        if (exists) {
            throw new GunsException(BizExceptionEnum.SELLER_ALREADY_REG);
        }
        seller.setCreatetime(new Date());
        baseMapper.insertUseGeneratedKeys(seller);
    }

    @Override
    public void edit(Seller seller) {
        baseMapper.updateByPrimaryKeySelective(seller);
    }

    @Override
    public void del(Integer id) {
        boolean result = couponMapper.existsBySellerid(id);
        if (result) {
            throw new GunsException(BizExceptionEnum.SELLER_HAS_COUPON);
        }
        baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int switchStatus(Integer id) {
        return baseMapper.switchStatus(id);
    }

    @Override
    public PageInfo<Seller> page(Seller seller, Integer pageNo, Integer pageSize, String sort) {
        //分页带排序功能
        PageHelper.startPage(pageNo, pageSize, sort);
        List<Seller> list = baseMapper.list(seller);
        PageInfo<Seller> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public List<ZTreeNode> getSellerTree() {
        List<ZTreeNode> list = baseMapper.getSellerTree();
        return list;
    }

//    @Override
//    public List<ZTreeNode> getSellerTreeByScope(Integer userId) {
//        boolean exists = userSellerDao.existsByUseridEquals(userId);
//        if (!exists) {
//            throw new GunsException(BizExceptionEnum.NO_SELLER_DATASCOPE);
//        }
//        List<Map<String, Object>> mapList = baseDao.getSellerTreeByScope(userId);
//        List<ZTreeNode> list = BeanKit.mapListTobeanList(mapList, ZTreeNode.class);
//        return list;
//    }
}
