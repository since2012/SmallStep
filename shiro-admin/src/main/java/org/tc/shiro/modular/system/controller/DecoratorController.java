package org.tc.shiro.modular.system.controller;

import com.stylefeng.guns.core.ztree.MenuNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tc.shiro.core.shiroext.kit.ShiroKit;
import org.tc.shiro.core.shiroext.vo.ShiroUser;

import java.util.List;

@Controller
@RequestMapping("/decorator/")
public class DecoratorController {

    /**
     * 装饰器
     *
     * @param model
     * @return
     */
    @RequestMapping("main")
    public String defaultDecorator(Model model) {
        ShiroUser shiroUser = ShiroKit.getUser();
        List<MenuNode> nodeList = shiroUser.getNodeList();
        model.addAttribute("titles", nodeList);
        //获取用户头像
        model.addAttribute("avatar", shiroUser.getAvatar());
        return "decorator/main";
    }
}