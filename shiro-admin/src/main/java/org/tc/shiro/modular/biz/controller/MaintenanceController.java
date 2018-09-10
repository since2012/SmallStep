package org.tc.shiro.modular.biz.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tc.mybatis.controller.BaseController;
import org.tc.mybatis.dto.GlobalResult;
import org.tc.shiro.po.Command;
import org.tc.shiro.service.CommandContentService;
import org.tc.shiro.service.CommandService;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/maintain")
public class MaintenanceController extends BaseController {

    private String PREFIX = "biz/maintain/";

    @Autowired
    private CommandService commandService;
    @Autowired
    private CommandContentService commandContentService;

    /**
     * 维护主页
     *
     * @return
     */
    @GetMapping("")
    public String index() {
        return PREFIX + "maintain";
    }

    /**
     * 查询列表
     *
     * @param command
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public Object list(Command command,
                       @RequestParam(required = false) Integer limit,
                       @RequestParam(required = false) Integer offset,
                       @RequestParam(required = false) String sort,
                       @RequestParam(required = false) String order) {
        if (StringUtils.isBlank(sort)) {
            sort = "name asc";
        } else {
            sort = sort + " " + order;
        }
        PageInfo<Command> page = commandService.page(command, offset / limit + 1, limit, sort);
        return super.warpForBT(page.getList(), page.getTotal());
    }

    /**
     * 维护新增
     *
     * @return
     */
    @GetMapping("/maintain_add")
    public String maintainAdd() {
        return PREFIX + "maintain_add";
    }

    /**
     * 新增
     *
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Command command, @RequestParam("content") List<String> content, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return GlobalResult.errorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        commandService.add(command, content);
        return SUCCESS_TIP;
    }

    /**
     * 维护编辑
     *
     * @return
     */
    @GetMapping("/maintain_edit")
    public String maintainEdit(Integer id, Model model) {
        Command command = commandService.selectByPK(id);
        List<String> contents = commandContentService.selectByCommandId(id);
        model.addAttribute("command", command);
        model.addAttribute("list", contents);
        return PREFIX + "maintain_edit";
    }

    /**
     * 新增
     *
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Command command, @RequestParam("content") List<String> content, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return GlobalResult.errorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        commandService.edit(command, content);
        return SUCCESS_TIP;
    }

    @PostMapping("/delete")
    @ResponseBody
    public Object deleteOne(Integer id) {
        commandService.delCascade(id);
        // 向页面跳转
        return SUCCESS_TIP;
    }

    /**
     * 经过测试（list,Array,String三者均可）
     *
     * @return
     */
    @PostMapping("/deleteBatch")
    @ResponseBody
    public Object deleteBatch(@RequestParam("id") List<Integer> idList) {
        commandService.deleteCascadeBatch(idList);
        // 向页面跳转
        return SUCCESS_TIP;
    }
}
