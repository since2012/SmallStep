package org.tc.shiro.mapper;

import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.Coupon;

import java.util.List;

public interface CouponMapper extends MyMapper<Coupon> {

    /**
     * 是否存在该卖家的数据
     *
     * @param sellerid
     * @return
     */
    public boolean existsBySellerid(@Param("sellerid") Integer sellerid);

    /**
     * 批量插入
     *
     * @param list
     * @return
     */
    public boolean insertBatch(List<Coupon> list);

    /**
     * 按条件查询
     *
     * @param coupon
     * @return
     */
    public List<Coupon> list(Coupon coupon);

    /**
     * 通过手机号码查询
     *
     * @param usertel
     * @return
     */
    public List<Coupon> mylist(@Param("usertel") String usertel);


    /**
     * 通过验证码查找
     *
     * @return
     */
    public Coupon selectByTelAndCode(@Param("usertel") String usertel, @Param("code") String code);

}