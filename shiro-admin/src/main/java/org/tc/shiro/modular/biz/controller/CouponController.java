package org.tc.shiro.modular.biz.controller;

import com.github.pagehelper.PageInfo;
import com.stylefeng.guns.core.util.ToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tc.mybatis.controller.BaseController;
import org.tc.mybatis.dto.GlobalResult;
import org.tc.mybatis.exception.GunsException;
import org.tc.shiro.core.common.exception.BizExceptionEnum;
import org.tc.shiro.modular.biz.service.ICouponService;
import org.tc.shiro.po.Coupon;
import org.tc.shiro.vo.CouponVo;
import org.tc.shiro.warpper.CouponWarpper;

import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * 优惠券控制器
 *
 * @author fengshuonan
 * @Date 2018-07-23 18:09:23
 */
@Controller
@RequestMapping("/coupon")
public class CouponController extends BaseController {

    private String PREFIX = "biz/coupon/";

    @Autowired
    private ICouponService couponService;

    /**
     * 跳转到优惠券首页
     */
    @GetMapping("")
    public String index() {
        return PREFIX + "coupon";
    }

    /**
     * 获取优惠券列表
     */
    @PostMapping("/list")
    @ResponseBody
    public Object list(Coupon coupon,
                       @RequestParam(required = false) Integer limit,
                       @RequestParam(required = false) Integer offset,
                       @RequestParam(required = false) String sort,
                       @RequestParam(required = false) String order) {
        if (StringUtils.isBlank(sort)) {
            sort = "valid desc,endday desc";
        } else {
            sort = sort + " " + order;
        }
        PageInfo<Coupon> page = couponService.page(coupon, offset / limit + 1, limit, sort);
        List<Coupon> list = page.getList();
        List<CouponVo> voList = new CouponWarpper().warpList(list);
        return super.warpForBT(voList, page.getTotal());
    }

    /**
     * 跳转到添加优惠券
     */
    @GetMapping("/add")
    public String couponAdd() {
        return PREFIX + "add";
    }


    /**
     * 新增优惠券
     */
    @PostMapping("/add")
    @ResponseBody
    public Object genCoupon(@Valid Coupon coupon, @RequestParam(required = false, defaultValue = "0") int total,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        if (coupon.getBeginday().compareTo(coupon.getEndday()) > 0) {
            throw new GunsException(BizExceptionEnum.COUPON_RANGE_ERROR);
        }
        if (total > 0) {
            couponService.genCoupon(coupon, total);
        } else {
            throw new GunsException(BizExceptionEnum.COUPON_NUM_ERROR);
        }
        return SUCCESS_TIP;
    }

    /**
     * 二维码页面
     *
     * @return
     */
    @GetMapping("/qrcode/{couponId}")
    public String qrcode(@PathVariable String couponId, Model model) {
        if (couponId == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        boolean exists = couponService.existsByPK(couponId);
        if (!exists) {
            throw new GunsException(BizExceptionEnum.DB_RESOURCE_NULL);
        }
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String ip = addr.getHostAddress().toString();
            model.addAttribute("address", "http://" + ip + ":8080/coupon/reception/" + couponId);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new GunsException(BizExceptionEnum.COUPON_QRCODE_ERROR);
        }
        return PREFIX + "qrcode";
    }

    /**
     * 删除优惠券
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam String couponId) {
        if (couponId == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        couponService.deleteCoupon(couponId);
        return SUCCESS_TIP;
    }

    /**
     * 使用优惠券
     *
     * @return
     */
    @PostMapping("use")
    @ResponseBody
    public Object use(String tel, String code) {
        if (ToolUtil.isOneEmpty(code, tel)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        couponService.use(code, tel);
        return SUCCESS_TIP;
    }

    /**
     * 消费者领取页面
     *
     * @param model
     * @return
     */
    @GetMapping("/reception/{couponId}")
    public String reception(@PathVariable("couponId") String couponId, Model model) {
        model.addAttribute("couponId", couponId);
        return PREFIX + "reception";
    }

    /**
     * 领取优惠券
     *
     * @return
     */
    @PostMapping("reception")
    @ResponseBody
    public Object recept(String usertel, String couponId) {
        if (ToolUtil.isOneEmpty(usertel, couponId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        String code = couponService.recept(usertel, couponId);
        return GlobalResult.ok(code);
    }

    /**
     * 查询页面
     *
     * @param model
     * @return
     */
    @GetMapping("mylist/{usertel}")
    public String mylist(@PathVariable String usertel, Model model) {
        if (StringUtils.isBlank(usertel)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        List<Coupon> list = couponService.mylist(usertel);
        model.addAttribute("list", new CouponWarpper().warpList(list));
        model.addAttribute("usertel", usertel);
        return PREFIX + "mylist";
    }

}
