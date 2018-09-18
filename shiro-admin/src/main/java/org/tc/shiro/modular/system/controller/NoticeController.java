package org.tc.shiro.modular.system.controller;

import com.stylefeng.guns.core.util.ToolUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tc.mybatis.controller.BaseController;
import org.tc.mybatis.exception.GunsException;
import org.tc.shiro.core.common.exception.BizExceptionEnum;
import org.tc.shiro.core.shiroext.kit.ShiroKit;
import org.tc.shiro.modular.system.service.INoticeService;
import org.tc.shiro.po.Notice;
import org.tc.shiro.warpper.NoticeWrapper;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 通知控制器
 *
 * @author fengshuonan
 * @Date 2017-05-09 23:02:21
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseController {

    private String PREFIX = "/system/notice/";

    @Autowired
    private INoticeService noticeService;

    /**
     * 跳转到通知列表首页
     */
    @GetMapping("")
    public String index() {
        return PREFIX + "notice";
    }

    /**
     * 获取通知列表
     */
    @PostMapping(value = "/list")
    @ResponseBody
    public Object list(String title, String content) {
        List<Notice> list = this.noticeService.list(title, content);
        return new NoticeWrapper().warpList(list);
    }

    /**
     * 跳转到添加通知
     */
    @GetMapping("/notice_add")
    public String noticeAdd() {
        return PREFIX + "notice_add";
    }

    /**
     * 新增通知
     */
    @PostMapping(value = "/add")
    @ResponseBody
    public Object add(@Valid Notice notice, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        notice.setCreater(ShiroKit.getUser().getId());
        notice.setCreatetime(new Date());
        noticeService.insertAllCol(notice);
        return SUCCESS_TIP;
    }

    /**
     * 跳转到修改通知
     */
    @GetMapping("/notice_edit/{noticeId}")
    public String noticeUpdate(@PathVariable Integer noticeId, Model model) {
        Notice notice = this.noticeService.selectByPK(noticeId);
        model.addAttribute("notice", notice);
        return PREFIX + "notice_edit";
    }

    /**
     * 修改通知
     */
    @PostMapping(value = "/edit")
    @ResponseBody
    public Object update(@Valid Notice notice, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Integer id = notice.getId();
        if (ToolUtil.isEmpty(id)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        noticeService.updateByPKSelective(notice);
        return SUCCESS_TIP;
    }

    /**
     * 删除通知
     */
    @PostMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer noticeId) {
        this.noticeService.deleteByPK(noticeId);
        return SUCCESS_TIP;
    }
}
