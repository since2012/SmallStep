package org.tc.shiro.modular.system.controller;

import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tc.mybatis.controller.BaseController;
import org.tc.mybatis.exception.GunsException;
import org.tc.mybatis.tips.Tip;
import org.tc.shiro.core.common.constant.AdminConst;
import org.tc.shiro.core.common.exception.BizExceptionEnum;
import org.tc.shiro.modular.system.service.IMenuService;
import org.tc.shiro.po.Menu;
import org.tc.shiro.warpper.MenuWarpper;

import javax.validation.Valid;
import java.util.List;

/**
 * 菜单控制器
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    private static String PREFIX = "system/menu/";

    @Autowired
    private IMenuService menuService;

    /**
     * 跳转到菜单列表列表页面
     */
    @GetMapping("")
    public String index() {
        return PREFIX + "menu";
    }

    /**
     * 获取菜单列表
     */
    @RequiresRoles(AdminConst.ADMIN_ROLE_NAME)
    @PostMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name,
                       @RequestParam(required = false) Integer level) {
        List<Menu> list = menuService.list(name, level);
        return new MenuWarpper().warpList(list);
    }

    /**
     * 跳转到菜单列表列表页面
     */
    @GetMapping(value = "/add")
    public String menuAdd() {
        return PREFIX + "add";
    }

    /**
     * 新增菜单
     */
    @RequiresRoles(AdminConst.ADMIN_ROLE_NAME)
    @PostMapping(value = "/add")
    @ResponseBody
    public Tip add(@Valid Menu menu, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        menuService.add(menu);
        return SUCCESS_TIP;
    }

    /**
     * 菜单修改页
     */
    @RequiresRoles(AdminConst.ADMIN_ROLE_NAME)
    @GetMapping(value = "/edit/{menuId}")
    public String menuEdit(@PathVariable Long menuId, Model model) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Menu menu = this.menuService.selectByPK(menuId);
        String pcode = menu.getPcode();
        String pcodeName = null;
        if (pcode == null || pcode.equals("0")) {
            menu.setPcode("0");
            pcodeName = "-";
        } else {
            //获取父级菜单的id
            Menu pmenu = menuService.getByCode(pcode);
            //如果父级是顶级菜单
            if (pmenu == null) {
                menu.setPcode("0");
                pcodeName = "-";
            } else {
                pcodeName = pmenu.getName();
            }
        }
        model.addAttribute("pcodeName", pcodeName);
        model.addAttribute("menu", menu);
        return PREFIX + "edit";
    }

    /**
     * 修该菜单
     */
    @RequiresRoles(AdminConst.ADMIN_ROLE_NAME)
    @PostMapping(value = "/edit")
    @ResponseBody
    public Tip edit(@Valid Menu menu, BindingResult result) {
        Long id = menu.getId();
        if (result.hasErrors() || id == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        menuService.update(menu);
        return SUCCESS_TIP;
    }

    /**
     * 状态切换
     */
    @RequiresRoles(AdminConst.ADMIN_ROLE_NAME)
    @PostMapping(value = "/switch")
    @ResponseBody
    public Tip switchStatus(@RequestParam Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //缓存菜单的名称
        this.menuService.switchStatusCascade(menuId);
        return SUCCESS_TIP;
    }

    /**
     * 删除菜单
     */
    @RequiresRoles(AdminConst.ADMIN_ROLE_NAME)
    @PostMapping(value = "/delete")
    @ResponseBody
    public Tip remove(@RequestParam Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //缓存菜单的名称
        this.menuService.delMenuCascade(menuId);
        return SUCCESS_TIP;
    }

//    /**
//     * 查看菜单
//     */
//    @RequestMapping(value = "/view/{menuId}")
//    @ResponseBody
//    public Tip view(@PathVariable Long menuId) {
//        if (ToolUtil.isEmpty(menuId)) {
//            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
//        }
//        this.menuService.selectByPK(menuId);
//        return SUCCESS_TIP;
//    }

    /**
     * 获取菜单列表(首页用，选择父级菜单用)
     */
    @PostMapping(value = "/tree")
    @ResponseBody
    public List<ZTreeNode> menuTreeList() {
        List<ZTreeNode> list = this.menuService.menuTree();
        list.add(ZTreeNode.createRoot());
        return list;
    }

    /**
     * 获取角色列表
     */
    @PostMapping(value = "/treeByRoleId/{roleId}")
    @ResponseBody
    public List<ZTreeNode> menuTreeByRoleId(@PathVariable Integer roleId) {
        List<ZTreeNode> list = menuService.getCheckedMenuTree(roleId);
        list.add(ZTreeNode.createRoot());
        return list;
    }

}
