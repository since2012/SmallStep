package org.tc.shiro.warpper;

import org.springframework.beans.BeanUtils;
import org.tc.mybatis.warpper.BeanWarpper;
import org.tc.shiro.core.common.constant.enums.DoubleStatus;
import org.tc.shiro.po.Seller;
import org.tc.shiro.vo.SellerVo;

/**
 * 卖家列表的包装类
 *
 * @author fengshuonan
 * @date 2017年2月19日15:07:29
 */
public class SellerWarpper extends BeanWarpper<Seller, SellerVo> {

    @Override
    public SellerVo warpBean(Seller po) {
        SellerVo vo = new SellerVo();
        BeanUtils.copyProperties(po, vo);
        vo.setStatusName(DoubleStatus.valueOf(po.getStatus()));
        return vo;
    }

}
