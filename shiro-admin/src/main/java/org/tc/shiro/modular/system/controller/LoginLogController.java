package org.tc.shiro.modular.system.controller;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tc.mybatis.controller.BaseController;
import org.tc.shiro.core.common.constant.AdminConst;
import org.tc.shiro.modular.system.service.ILoginLogService;
import org.tc.shiro.po.LoginLog;
import org.tc.shiro.vo.LoginLogCountVo;
import org.tc.shiro.vo.LoginLogVo;
import org.tc.shiro.warpper.LoginLogWarpper;

import java.util.List;

/**
 * 登录日志的控制器
 *
 * @author fengshuonan
 * @Date 2017年4月5日 19:45:36
 */
@Controller
@RequestMapping("/login_log")
public class LoginLogController extends BaseController {

    private static String PREFIX = "system/log/";

    @Autowired
    private ILoginLogService loginLogService;

    /**
     * 跳转到日志管理的首页
     */
    @GetMapping("")
    public String index() {
        return PREFIX + "login_log";
    }

    /**
     * 查询登录日志列表
     */
    @PostMapping("/list")
    @RequiresRoles(AdminConst.ADMIN_ROLE_NAME)
    @ResponseBody
    public Object list(@RequestParam(required = false) String logName,
                       @RequestParam(required = false) String message,
                       @RequestParam(required = false) String beginTime,
                       @RequestParam(required = false) String endTime,
                       @RequestParam(required = false) Integer limit,
                       @RequestParam(required = false) Integer offset,
                       @RequestParam(required = false) String sort,
                       @RequestParam(required = false) String order) {

        if (StringUtils.isBlank(sort)) {
            sort = "createtime desc";
        } else {
            sort = sort + " " + order;
        }
        PageInfo<LoginLog> page = loginLogService.page(logName, message, beginTime, endTime, offset / limit + 1, limit, sort);
        List<LoginLog> content = page.getList();
        List<LoginLogVo> list = new LoginLogWarpper().warpList(content);
        return super.warpForBT(list, page.getTotal());
    }

    /**
     * echart
     */
    @PostMapping("/line")
    @ResponseBody
    public Object line() {
        LoginLogCountVo option = loginLogService.countByDay();
        return option;
    }

    /**
     * 清空日志
     */
    @PostMapping("/clear")
    @RequiresRoles(AdminConst.ADMIN_ROLE_NAME)
    @ResponseBody
    public Object delLog() {
        loginLogService.deleteAll();
        return SUCCESS_TIP;
    }
}
