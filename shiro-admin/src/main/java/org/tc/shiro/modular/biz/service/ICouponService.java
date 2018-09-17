package org.tc.shiro.modular.biz.service;

import com.github.pagehelper.PageInfo;
import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.Coupon;

import java.util.List;

/**
 * 优惠券Service
 *
 * @author TC
 * @Date 2018-07-29 16:44:13
 */
public interface ICouponService extends IBaseService<Coupon> {

    /**
     * 生成优惠券
     *
     * @param coupon
     * @param total
     * @return
     */
    public void genCoupon(Coupon coupon, int total);

    /**
     * 删除未领取的数据
     *
     * @param couponId
     * @return
     */
    public void deleteCoupon(String couponId);

    /**
     * 领取优惠券
     *
     * @return
     */
    public String recept(String userTel, String id);

    /**
     * 使用优惠券
     *
     * @return
     */
    public void use(String code, String usertel);

    /**
     * 分页查询
     *
     * @param pageSize
     * @return
     */
    PageInfo<Coupon> page(Coupon coupon, Integer pageNo, Integer pageSize, String sort);

    /**
     * 根据手机号查询
     */
    List<Coupon> mylist(String usertel);

}
