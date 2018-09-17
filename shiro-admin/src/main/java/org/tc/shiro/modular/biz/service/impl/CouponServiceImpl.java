package org.tc.shiro.modular.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stylefeng.guns.core.util.ToolUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tc.mybatis.exception.GunsException;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.core.common.constant.enums.DoubleStatus;
import org.tc.shiro.core.common.exception.BizExceptionEnum;
import org.tc.shiro.mapper.CouponMapper;
import org.tc.shiro.modular.biz.service.ICouponService;
import org.tc.shiro.po.Coupon;

import java.util.*;

/**
 * 优惠券Service实现
 *
 * @author TC
 * @Date 2018-07-29 16:44:14
 */
@Service
public class CouponServiceImpl extends BaseServiceImpl<CouponMapper, Coupon> implements ICouponService {

    /**
     * 生成批量数据
     *
     * @param coupon
     * @param num
     * @return
     */
    public List<Coupon> createList(Coupon coupon, int num) {
        coupon.setValid(Integer.valueOf(1));
        Date date = new Date();
        coupon.setCreatetime(date);
        ArrayList<Coupon> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Coupon temp = new Coupon();
            BeanUtils.copyProperties(coupon, temp);
            temp.setId(UUID.randomUUID().toString());
            list.add(temp);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void genCoupon(Coupon coupon, int total) {
        List<Coupon> list = createList(coupon, total);
        baseMapper.insertBatch(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCoupon(String couponId) {
        Coupon coupon = baseMapper.selectByPrimaryKey(couponId);
        if (coupon.getUsertel() != null) {
            throw new GunsException(BizExceptionEnum.COUPON_CANT_DELETE);
        }
        baseMapper.deleteByPrimaryKey(couponId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String recept(String userTel, String id) {
        Coupon coupon = baseMapper.selectByPrimaryKey(id);
        //已领取
        if (coupon.getUsertel() != null) {
            if (userTel.equals(coupon.getUsertel())) {
                throw new GunsException(BizExceptionEnum.COUPON_RECEPTED);
            }
            throw new GunsException(BizExceptionEnum.COUPON_OTHER_RECEPTED);
        } else {
            Date date = new Date();
            date = DateUtils.truncate(date, Calendar.DATE);
            int result = date.compareTo(coupon.getEndday());
            //过期了
            if (result > 0) {
                throw new GunsException(BizExceptionEnum.COUPON_EXPIRED);
            }
            coupon.setUsertel(userTel);
            coupon.setReceptday(new Date());
            String code = ToolUtil.getRandomString(5).toUpperCase();
            coupon.setCode(code);
            baseMapper.updateByPrimaryKeySelective(coupon);
            return code;
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void use(String code, String usertel) {
        Coupon coupon = baseMapper.selectByTelAndCode(usertel, code);
        if (coupon == null) {
            throw new GunsException(BizExceptionEnum.DB_RESOURCE_NULL);
        }
        String useCode = coupon.getCode();
        if (useCode != null) {
            Date date = new Date();
            date = DateUtils.truncate(date, Calendar.DATE);
            int result = date.compareTo(coupon.getEndday());
            //过期了
            if (result > 0) {
                throw new GunsException(BizExceptionEnum.COUPON_EXPIRED);
            }
            if (useCode.equals(code.trim())) {
                coupon.setValid(DoubleStatus.DISABLE.getCode());
                baseMapper.updateByPrimaryKeySelective(coupon);
            } else {
                throw new GunsException(BizExceptionEnum.COUPON_CODE_ERROR);
            }
        } else {
            throw new GunsException(BizExceptionEnum.COUPON_NOT_ACTIVED);
        }
    }

    @Override
    public PageInfo<Coupon> page(Coupon coupon, Integer pageNo, Integer pageSize, String sort) {
        PageHelper.startPage(pageNo, pageSize, sort);
        List<Coupon> list = baseMapper.list(coupon);
        PageInfo<Coupon> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public List<Coupon> mylist(String usertel) {
        return this.baseMapper.mylist(usertel);
    }
}
