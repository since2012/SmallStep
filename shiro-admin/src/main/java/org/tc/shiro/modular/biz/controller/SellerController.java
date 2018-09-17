package org.tc.shiro.modular.biz.controller;

import com.github.pagehelper.PageInfo;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tc.mybatis.controller.BaseController;
import org.tc.mybatis.exception.GunsException;
import org.tc.shiro.core.common.constant.AdminConst;
import org.tc.shiro.core.common.exception.BizExceptionEnum;
import org.tc.shiro.core.shiroext.kit.ShiroKit;
import org.tc.shiro.modular.biz.service.ISellerService;
import org.tc.shiro.po.Seller;
import org.tc.shiro.vo.SellerVo;
import org.tc.shiro.warpper.SellerWarpper;

import javax.validation.Valid;
import java.util.List;

/**
 * 卖家信息控制器
 *
 * @author fengshuonan
 * @Date 2018-07-23 11:21:28
 */
@Controller
@RequestMapping("/seller")
public class SellerController extends BaseController {

    private String PREFIX = "biz/seller/";

    @Autowired
    private ISellerService sellerService;

    /**
     * 跳转到卖家信息首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "seller";
    }

    /**
     * 获取卖家信息列表
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public Object list(Seller seller,
                       @RequestParam(required = false) Integer limit,
                       @RequestParam(required = false) Integer offset,
                       @RequestParam(required = false) String sort,
                       @RequestParam(required = false) String order) {
        if (StringUtils.isBlank(sort)) {
            sort = " createtime asc,status desc";
        } else {
            sort = sort + " " + order;
        }
        PageInfo<Seller> page = sellerService.page(seller, offset / limit + 1, limit, sort);
        List<Seller> list = page.getList();
        List<SellerVo> voList = new SellerWarpper().warpList(list);
        return super.warpForBT(voList, page.getTotal());
    }

    /**
     * 跳转到添加卖家信息
     */
    @GetMapping("/seller_add")
    public String sellerAdd() {
        return PREFIX + "seller_add";
    }

    /**
     * 新增卖家信息
     */
    @PostMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid Seller seller, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        sellerService.add(seller);
        return SUCCESS_TIP;
    }

    /**
     * 跳转到修改卖家信息
     */
    @GetMapping("/seller_edit/{sellerId}")
    public String sellerUpdate(@PathVariable Integer sellerId, Model model) {
        Seller seller = sellerService.selectByPK(sellerId);
        model.addAttribute("seller", seller);
        return PREFIX + "seller_edit";
    }

    /**
     * 修改卖家信息
     */
    @PostMapping(value = "/edit")
    @ResponseBody
    public Object update(@Valid Seller seller, BindingResult bindingResult) {
        Integer id = seller.getId();
        if (bindingResult.hasErrors() || id == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        sellerService.edit(seller);
        return SUCCESS_TIP;
    }

    /**
     * 删除卖家信息(假删)
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer sellerId) {
        if (sellerId == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        sellerService.del(sellerId);
        return SUCCESS_TIP;
    }


    /**
     * 切换状态
     */
    @PostMapping(value = "/switch")
    @ResponseBody
    public Object change(@RequestParam Integer sellerId) {
        if (sellerId == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        sellerService.switchStatus(sellerId);
        return SUCCESS_TIP;
    }

    /**
     * 卖家列表(查看使用)
     */
    @RequestMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> roleTreeListByUserId() {
        //超级管理员不受约束
        if (ShiroKit.isAdmin()) {
            return sellerService.getSellerTree();
        }
        if (ShiroKit.hasRole(AdminConst.SELLER_NAME)) {
            return sellerService.getSellerTree();
        }
        throw new GunsException(BizExceptionEnum.NO_PERMITION);
    }

}
