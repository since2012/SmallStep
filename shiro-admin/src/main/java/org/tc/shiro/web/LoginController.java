package org.tc.shiro.web;

import com.baomidou.kaptcha.Kaptcha;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tc.mybatis.dto.GlobalResult;
import org.tc.shiro.po.SysUser;
import org.tc.shiro.service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 登录
 *
 * @author user
 */
@Controller
@RequestMapping(value = "/")
@Slf4j
public class LoginController {

    @Autowired
    private IUserService userService;
    @Autowired
    private Kaptcha kaptcha;

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login() {
        //若为登录状态
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:admin/user/list";
        }
        return "login";
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    /**
     * 登录验证
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResult login(String username, HttpServletRequest req) {
        return GlobalResult.ok();
    }

    /**
     * 获取验证码
     *
     * @param request
     * @param response
     */
    @GetMapping("kaptcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        kaptcha.init(request, response);
        kaptcha.render();
    }

    /**
     * 注销
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(Model model) {
        return "login";
    }

    /**
     * 注册用户
     *
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @PostMapping("regist")
    @ResponseBody
    public GlobalResult regist(String username, String password, HttpServletRequest request, HttpServletResponse
            response) {

        SysUser user = new SysUser();
        user.setUsername(username);
        String pw = new Md5Hash(user.getPassword(), username, 2).toString();
        user.setPassword(pw);
        user.setNickname(username);

        // 验证一致，先注册用户
        boolean registFlag = userService.insertUseGeneratedKeys(user);
        if (!registFlag) {
            return GlobalResult.errorMsg("注册失败！请返回首页重新注册.");
        }

        // 为了让用户在注册成功，直接访问首页，所以在注册超成功后，需要手动执行登陆
        Subject userShiro = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
        userShiro.login(token);

        return GlobalResult.ok();
    }

    /**
     * 未授权提示
     *
     * @return
     */
    @RequestMapping(value = "unAuthorization")
    public String unAuthorization() {
        return "unAuthorization";
    }
}
