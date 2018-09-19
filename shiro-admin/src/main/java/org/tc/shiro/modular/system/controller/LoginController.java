package org.tc.shiro.modular.system.controller;

import com.baomidou.kaptcha.Kaptcha;
import com.stylefeng.guns.core.util.ToolUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tc.mybatis.controller.BaseController;
import org.tc.shiro.core.shiroext.kit.ShiroKit;
import org.tc.shiro.core.shiroext.vo.ShiroUser;
import org.tc.shiro.modular.system.service.INoticeService;
import org.tc.shiro.po.Notice;
import org.tc.shiro.warpper.NoticeWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 登录控制器
 *
 * @author fengshuonan
 * @Date 2017年1月10日 下午8:25:24
 */
@Controller
public class LoginController extends BaseController {

    @Autowired
    private Kaptcha kaptcha;
    @Autowired
    private INoticeService noticeService;

    /**
     * 登录页面
     */
    @GetMapping("/login")
    public String login() {
        if (ShiroKit.isAuthenticated() || ShiroKit.getUser() != null) {
            return REDIRECT + "/";
        } else {
            return "login";
        }
    }

    /**
     * 点击登录执行的动作（登录过滤器）
     */
    @PostMapping("/login")
    @ResponseBody
    public Object loginVali() {
        return SUCCESS_TIP;
    }

    /**
     * 退出（下线过滤器）
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut() {
        deleteAllCookie();
        return REDIRECT + "/login";
    }

    /**
     * 根路径
     */
    @GetMapping("/")
    public String root(Model model) {

        //未登录
        ShiroUser shiroUser = ShiroKit.getUser();
        if (ToolUtil.isEmpty(shiroUser)) {
            model.addAttribute("tips", "你还没有登录");
            return REDIRECT + "login";
        }

        //未分配角色
        List<Integer> roleList = shiroUser.getRoleList();
        if (CollectionUtils.isEmpty(roleList)) {
            ShiroKit.getSubject().logout();
            model.addAttribute("tips", "该用户没有角色，无法登陆");
            return REDIRECT + "login";
        }
        return REDIRECT + "index";
    }

    /**
     * 根路径
     */
    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    /**
     * 黑板
     */
    @GetMapping("/blackboard")
    public String blackboard(Model model) {
        List<Notice> notices = noticeService.list(null, null);
        model.addAttribute("list", new NoticeWrapper().warpList(notices));
        return "blackboard";
    }

    /**
     * 验证码
     */
    @RequestMapping("/kaptcha")
    public void kaptcha(HttpServletRequest request, HttpServletResponse response) {
        kaptcha.init(request, response);
        kaptcha.render();
    }


    /**
     * 注册页
     *
     * @return
     */
    @GetMapping("register")
    public String register() {
        return "register";
    }

    /**
     * 锁屏页
     *
     * @return
     */
    @GetMapping("lockscreen")
    public String lockscreen() {
        return "lockscreen";
    }

    /**
     * 404
     *
     * @return
     */
    @GetMapping("404")
    public String notFound() {
        return "404";
    }

    /**
     * 500
     *
     * @return
     */
    @GetMapping("500")
    public String serverError() {
        return "500";
    }
}
