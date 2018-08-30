package org.tc.shiro.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tc.mybatis.dto.GlobalResult;
import org.tc.shiro.po.SysResource;
import org.tc.shiro.po.SysUser;
import org.tc.shiro.service.IResService;
import org.tc.shiro.service.IRoleService;
import org.tc.shiro.service.IUserService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping(value = "/admin/user/")
@Slf4j
public class UserController {

    @Autowired
    private IUserService baseService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IResService resourceService;

    /**
     * 返回到列表显示页面
     *
     * @return
     */
    @GetMapping("list")
    public String list(Model model) {
        // 查询到所有的数据
        List<SysUser> list = baseService.selectAll();
        model.addAttribute("list", list);
        return "user/list";
    }

    /**
     * 跳转到添加用户的页面
     *
     * @param model
     * @return
     */
    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("list", roleService.selectAll());
        return "user/add";
    }

    /**
     * 添加用户保存的方法
     *
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public GlobalResult add(@Valid SysUser user, @RequestParam("roleId") List<Byte> roleIdList,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return GlobalResult.errorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        user.setCreateTime(new Date());
        boolean result = baseService.insert(user, roleIdList);
        return GlobalResult.tip(result);
    }

    /**
     * 跳转到用户信息更新页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    @PostMapping("delete")
    public String update(@PathVariable("id") Byte id, Model model) {
        // 要从数据库查询对象进行回显
        SysUser user = baseService.selectByPK(id);

        /**
         * 根据用户 id 查询用户的所有角色
         */
        List<Byte> hasRoles = roleService.selectRoleByUid(id);

        model.addAttribute("entity", user);
        // 所有的角色列表
        model.addAttribute("list", roleService.selectAll());
        // 指定用户拥有的角色信息
        model.addAttribute("hasRole", hasRoles);
        return "user/update";
    }

    /**
     * 更新用户的信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @PostMapping("delete")
    @ResponseBody
    public GlobalResult update(@Valid SysUser user, @RequestParam("roleId") List<Byte> roleIdList,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return GlobalResult.errorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        boolean result = baseService.update(user, roleIdList);
        return GlobalResult.tip(result);
    }

    /**
     * 查看用户权限
     *
     * @return
     */
    @GetMapping("resources/{id}")
    public String listResources(@PathVariable("id") Byte userId, Model model) {
        List<SysResource> resourceList = resourceService.selectListByUid(userId);
        SysUser user = baseService.selectByPK(userId);
        model.addAttribute("list", resourceList);
        model.addAttribute("entity", user);
        return "user/resources";
    }

    /**
     * 切换状态
     *
     * @param id
     * @return
     */
    @ResponseBody
    @PostMapping("switch")
    public GlobalResult updateStatus(Byte id) {
        if (id == null) {
            return GlobalResult.errorMsg("缺失用户ID");
        }
        boolean result = baseService.existsByPK(id);
        if (!result) {
            return GlobalResult.errorMsg("该角色不存在");
        }
        SysUser sysUser = baseService.selectByPK(id);
        sysUser.setEnable(!sysUser.getEnable());
        sysUser.setEditTime(new Date());
        result = baseService.updateAllColByPK(sysUser);
        return GlobalResult.tip(result);
    }

    /**
     * 批量禁用用户
     *
     * @return
     */
    @ResponseBody
    @PostMapping("diable")
    public GlobalResult diable(@RequestParam("itemId") List<Byte> itemIds) {
        boolean result = baseService.disableByIdList(itemIds);
        return GlobalResult.tip(result);
    }

    /**
     * 批量删除用户
     *
     * @return
     */
    @ResponseBody
    @PostMapping("delete")
    public GlobalResult delete(@RequestParam("itemId") List<Byte> itemIds) {
        boolean result = baseService.deleteByIdList(itemIds);
        return GlobalResult.tip(result);
    }
}
