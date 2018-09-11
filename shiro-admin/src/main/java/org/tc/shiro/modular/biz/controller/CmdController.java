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
import org.tc.shiro.po.Cmd;
import org.tc.shiro.service.CmdContentService;
import org.tc.shiro.service.CmdService;
import org.tc.shiro.vo.CmdVo;
import org.tc.shiro.warpper.CmdWarpper;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/cmd")
public class CmdController extends BaseController {

    private String PREFIX = "biz/cmd/";

    @Autowired
    private CmdService cmdService;
    @Autowired
    private CmdContentService cmdContentService;

    /**
     * 维护主页
     *
     * @return
     */
    @GetMapping("")
    public String index() {
        return PREFIX + "cmd";
    }

    /**
     * 查询列表
     *
     * @param cmd
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public Object list(Cmd cmd,
                       @RequestParam(required = false) Integer limit,
                       @RequestParam(required = false) Integer offset,
                       @RequestParam(required = false) String sort,
                       @RequestParam(required = false) String order) {
        if (StringUtils.isBlank(sort)) {
            sort = "name asc";
        } else {
            sort = sort + " " + order;
        }
        PageInfo<Cmd> page = cmdService.page(cmd, offset / limit + 1, limit, sort);
        List<Cmd> list = page.getList();
        List<CmdVo> voList = new CmdWarpper().warpList(list);
        return super.warpForBT(voList, page.getTotal());
    }

    /**
     * 维护新增
     *
     * @return
     */
    @GetMapping("/cmd_add")
    public String cmdAdd() {
        return PREFIX + "cmd_add";
    }

    /**
     * 新增
     *
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Cmd cmd, @RequestParam("content") List<String> content, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return GlobalResult.errorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        cmdService.add(cmd, content);
        return SUCCESS_TIP;
    }

    /**
     * 维护编辑
     *
     * @return
     */
    @GetMapping("/cmd_edit")
    public String cmdEdit(Integer id, Model model) {
        Cmd cmd = cmdService.selectByPK(id);
        List<String> contents = cmdContentService.selectByCmdId(id);
        model.addAttribute("cmd", cmd);
        model.addAttribute("list", contents);
        return PREFIX + "cmd_edit";
    }

    /**
     * 新增
     *
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid Cmd cmd, @RequestParam("content") List<String> content, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return GlobalResult.errorMsg(bindingResult.getFieldError().getDefaultMessage());
        }
        cmdService.edit(cmd, content);
        return SUCCESS_TIP;
    }

    @PostMapping("/delete")
    @ResponseBody
    public Object deleteOne(Integer id) {
        cmdService.delCascade(id);
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
        cmdService.deleteCascadeBatch(idList);
        // 向页面跳转
        return SUCCESS_TIP;
    }
}
