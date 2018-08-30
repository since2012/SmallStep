package org.tc.shiro.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tc.mybatis.dto.GlobalResult;
import org.tc.shiro.po.SysResource;
import org.tc.shiro.po.SysRole;
import org.tc.shiro.service.IResService;
import org.tc.shiro.service.IRoleService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/role/")
public class RoleController {

    @Autowired
    private IRoleService baseService;
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
        List<SysRole> list = baseService.selectAll();
        model.addAttribute("list", list);
        return "role/list";
    }

    /**
     * 跳转到添加用户的页面
     *
     * @param model
     * @return
     */
    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("list", baseService.selectAll());
        return "role/add";
    }

    /**
     * 保存
     *
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public GlobalResult add(@Valid SysRole entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return GlobalResult.errorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        entity.setCreateTime(new Date());
        boolean result = baseService.insertUseGeneratedKeys(entity);
        return GlobalResult.tip(result);
    }

    /**
     * 跳转到更新页面
     *
     * @param id
     * @return
     */
    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Byte id, Model model) {
        boolean result = baseService.existsByPK(id);
        if (result) {
            SysRole entity = baseService.selectByPK(id);
            model.addAttribute("entity", entity);
        }
        return "role/update";
    }

    /**
     * 更新角色的方法
     *
     * @param entity
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    public GlobalResult update(@Valid SysRole entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return GlobalResult.errorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        boolean result = baseService.existsByPK(entity.getId());
        if (result) {
            entity.setEditTime(new Date());
            result = baseService.updateByPKSelective(entity);
        }
        return GlobalResult.tip(result);
    }


    /**
     * 禁用权限的方法
     *
     * @return
     */
    @PostMapping("switch")
    @ResponseBody
    public GlobalResult enable(Byte id) {
        if (id == null) {
            return GlobalResult.errorMsg("缺少角色ID");
        }
        boolean result = baseService.existsByPK(id);
        if (!result) {
            return GlobalResult.errorMsg("该角色不存在");
        }
        SysRole sysRole = baseService.selectByPK(id);
        sysRole.setEnable(!sysRole.getEnable());
        sysRole.setEditTime(new Date());
        result = baseService.updateAllColByPK(sysRole);
        return GlobalResult.tip(result);
    }

    /**
     * 配置角色权限页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("resources/{id}")
    public String listResources(@PathVariable("id") Byte id, Model model) {
        /**
         * 查询这个角色拥有的资源集合
         */
        List<Byte> idList = baseService.selectResIdsByRid(id);

        // 查询所有资源列表
        List<SysResource> resourceList = resourceService.selectAll();
        // 查询角色对象
        SysRole role = baseService.selectByPK(id);

        model.addAttribute("hasResourceIds", idList);
        model.addAttribute("list", resourceList);
        model.addAttribute("entity", role);
        return "role/resources";
    }

    /**
     * 设置角色权限
     *
     * @return
     */
    @PostMapping("resources")
    @ResponseBody
    public GlobalResult resource(Byte roleId, @RequestParam("itemIds") List<Byte> itemIds) {
        if (roleId == null) {
            return GlobalResult.errorMsg("缺失角色ID");
        }
        boolean result = baseService.updateResource(roleId, itemIds);
        return GlobalResult.tip(result);
    }

    /**
     * @param itemIds
     */
    @ResponseBody
    @PostMapping("diable")
    public GlobalResult diable(@RequestParam("itemId") List<Byte> itemIds) {
        boolean result = baseService.disableBatchByIds(itemIds);
        return GlobalResult.tip(result);
    }

    /**
     * @param itemIds
     */
    @ResponseBody
    @PostMapping("delete")
    public GlobalResult deleteRole(@RequestParam("itemId") List<Byte> itemIds) {
        boolean result = baseService.deleteBatchByIds(itemIds);
        return GlobalResult.tip(result);
    }
}
