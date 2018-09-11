package org.tc.shiro.modular.biz.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tc.mybatis.controller.BaseController;
import org.tc.mybatis.dto.GlobalResult;
import org.tc.shiro.service.CmdService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
@Slf4j
@RequestMapping("/robot")
public class RobotController extends BaseController {

    private String PREFIX = "biz/robot/";

    @Autowired
    private CmdService cmdService;

    /**
     * 对话页面
     *
     * @return
     */
    @GetMapping("")
    public String index() {
        return PREFIX + "robot";
    }

    /**
     * 自动回复
     *
     * @param message
     */
    @PostMapping("/reply")
    @ResponseBody
    public GlobalResult reply(String message) {
        try {
            message = URLDecoder.decode(message, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String data = cmdService.reply(message);
        return GlobalResult.ok(data);
    }

}
