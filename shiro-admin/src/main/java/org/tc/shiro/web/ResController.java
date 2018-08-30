package org.tc.shiro.web;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tc.mybatis.dto.GlobalResult;
import org.tc.shiro.po.SysResource;
import org.tc.shiro.service.IResService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/resource/")
public class ResController {

    @Autowired
    protected IResService resService;

    /**
     * 返回到列表显示页面
     *
     * @return
     */
    @GetMapping("list")
    public String list(Model model) {
        // 查询到所有的数据
        List<SysResource> list = resService.selectAll();
        model.addAttribute("list", list);
        return "resource/list";
    }

    /**
     * 跳转到添加权限的页面
     *
     * @return
     */
    @GetMapping("add")
    public String add(Model model) {
        return "resource/add";
    }

    /**
     * 跳转到添加权限的页面
     *
     * @return
     */
    @PostMapping("add")
    @ResponseBody
    public GlobalResult add(@Valid SysResource entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return GlobalResult.errorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        entity.setCreateTime(new Date());
        boolean result = resService.insertUseGeneratedKeys(entity);
        return GlobalResult.tip(result);
    }

    /**
     * 跳转到更新权限的页面
     *
     * @param id
     * @return
     */
    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Byte id, Model model) {
        if (id != null) {
            boolean result = resService.existsByPK(id);
            if (result) {
                SysResource entity = resService.selectByPK(id);
                model.addAttribute("entity", entity);
            }
        }
        return "resource/update";
    }

    /**
     * 更新权限的方法
     *
     * @param entity
     * @return
     */
    @PostMapping("update")
    @ResponseBody
    public GlobalResult update(@Valid SysResource entity, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return GlobalResult.errorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        entity.setEditTime(new Date());
        boolean result = resService.updateByPKSelective(entity);
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
            return GlobalResult.errorMsg("缺少资源ID");
        }
        boolean result = resService.existsByPK(id);
        if (!result) {
            return GlobalResult.errorMsg("该权限不存在");
        }
        SysResource resource = resService.selectByPK(id);
        resource.setEnable(!resource.getEnable());
        resource.setEditTime(new Date());
        result = resService.updateAllColByPK(resource);
        return GlobalResult.tip(result);
    }

    /**
     * 批量禁用权限的方法
     * JS数组要使用[]标识
     *
     * @return
     */
    @PostMapping("diable")
    @ResponseBody
    public GlobalResult diable(@RequestParam("itemIds[]") List<Byte> itemIds) {
        if (CollectionUtils.isEmpty(itemIds)) {
            return GlobalResult.errorMsg("未选择要删除的数据");
        }
        boolean result = resService.diableBatchByIds(itemIds);
        return GlobalResult.tip(result);
    }

    /**
     * 批量删除权限的方法
     * JS数组要使用[]标识
     *
     * @return
     */
    @PostMapping("delete")
    @ResponseBody
    public GlobalResult delete(@RequestParam("itemId") List<Byte> itemIds) {
        if (CollectionUtils.isEmpty(itemIds)) {
            return GlobalResult.errorMsg("未选择要删除的数据");
        }
        boolean result = resService.deleteBatchByIds(itemIds);
        return GlobalResult.tip(result);
    }

}
