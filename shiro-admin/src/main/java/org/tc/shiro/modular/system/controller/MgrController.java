package org.tc.shiro.modular.system.controller;

import com.stylefeng.guns.core.util.ToolUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tc.mybatis.controller.BaseController;
import org.tc.mybatis.exception.GunsException;
import org.tc.mybatis.tips.Tip;
import org.tc.mybatis.util.FileUtil;
import org.tc.redis.cache.RedisCacheDao;
import org.tc.shiro.config.properties.GunsProperties;
import org.tc.shiro.core.common.annotion.BizLog;
import org.tc.shiro.core.common.annotion.BizNameType;
import org.tc.shiro.core.common.constant.AdminConst;
import org.tc.shiro.core.common.constant.enums.TrebleStatus;
import org.tc.shiro.core.common.constant.factory.ConstantFactory;
import org.tc.shiro.core.common.exception.BizExceptionEnum;
import org.tc.shiro.core.shiroext.kit.ShiroKit;
import org.tc.shiro.core.shiroext.vo.ShiroUser;
import org.tc.shiro.po.User;
import org.tc.shiro.service.IUserService;
import org.tc.shiro.warpper.UserWarpper;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * 系统管理员控制器
 *
 * @author fengshuonan
 * @Date 2017年1月11日 下午1:08:17
 */
@Controller
@RequestMapping("/mgr")
public class MgrController extends BaseController {

    private static String PREFIX = "system/user/";


    @Autowired
    private IUserService userService;
    @Autowired
    private GunsProperties gunsProperties;
    @Autowired
    private RedisCacheDao redisCacheDao;

    /**
     * 用户管理首页
     */
    @GetMapping("")
    public String index() {
        return PREFIX + "user";
    }

    /**
     * 获取用户列表
     */
    @PostMapping("/list")
    @ResponseBody
    public Object list(User user,
                       @RequestParam(required = false) String beginTime,
                       @RequestParam(required = false) String endTime) {
        List<User> list = userService.list(user, beginTime, endTime);
        return new UserWarpper().warpList(list);
    }

    /**
     * 跳转到查看管理员列表的页面
     */
    @GetMapping("/user_add")
    public String addView() {
        return PREFIX + "user_add";
    }

    /**
     * 添加管理员
     */
    @PostMapping("/add")
    @BizLog(value = "管理员", type = BizNameType.ADD)
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Tip add(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        userService.add(user);
        return SUCCESS_TIP;
    }

    /**
     * 跳转到编辑管理员页面
     */
    @GetMapping("/user_edit/{userId}")
    public String userEdit(@PathVariable Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userService.selectByPK(userId);
        ShiroKit.assertAuth(userId, user);
        model.addAttribute("user", user);
        model.addAttribute("roleName", ConstantFactory.me().getMultiRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        return PREFIX + "user_edit";
    }

    /**
     * 修改（超级管理员操作，或变更自身信息）
     */
    @PostMapping("/edit")
    @BizLog(value = "管理员", key = "id", type = BizNameType.UPDATE)
    @ResponseBody
    public Tip edit(@Valid User user, BindingResult result) {
        if (result.hasErrors() || user.getId() == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        int id = user.getId();
        User oldUser = userService.selectByPK(id);
        //超级管理员
        if (ShiroKit.hasRole(AdminConst.ADMIN_NAME)) {
            userService.edit(user, oldUser);
        } else {
            //部门不可随意扩大
            ShiroKit.assertAuth(id, oldUser);
            ShiroUser shiroUser = ShiroKit.getUser();
            //当前用户自身
            if (shiroUser.getId().equals(id)) {
                userService.edit(user, oldUser);
            } else {
                throw new GunsException(BizExceptionEnum.NO_PERMITION);
            }
        }
        return SUCCESS_TIP;
    }

    /**
     * 用户详情页面
     */
    @GetMapping("/profile")
    public String userInfo(Model model) {
        Integer userId = ShiroKit.getUser().getId();
        if (userId == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userService.selectByPK(userId);
        model.addAttribute("user", user);
        model.addAttribute("roleName", ConstantFactory.me().getMultiRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        return PREFIX + "profile";
    }

    /**
     * 当前用户修改密码
     */
    @PostMapping("/changePwd")
    @ResponseBody
    public Object changePwd(@RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String rePwd) {
        userService.changePwd(oldPwd, newPwd, rePwd);
        return SUCCESS_TIP;
    }

    /**
     * 返回头像
     *
     * @author stylefeng
     * @Date 2017/5/24 23:00
     */
    @GetMapping("/img/{pictureId}")
    public void renderPicture(HttpServletRequest request, HttpServletResponse response) {
        //规避了获取不到文件后缀的问题
        String url = WebUtils.getPathWithinApplication(request);
        int lastIndexOf = url.lastIndexOf("/");
        String filename = url.substring(lastIndexOf + 1);
        String path = gunsProperties.getFileUploadPath() + filename;
        try {
            byte[] bytes = FileUtil.toByteArray(path);
            response.getOutputStream().write(bytes);
        } catch (Exception e) {
            //如果找不到图片就返回一个默认图片
            try {
                response.sendRedirect("/static/dist/img/user8-128x128.jpg");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 上传头像
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(@RequestPart("file") MultipartFile multipartFile) {

        //step1:保存图片
        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(multipartFile.getOriginalFilename());
        //配置的保存地址
        String fileSavePath = gunsProperties.getFileUploadPath();
        try {
            multipartFile.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new GunsException(BizExceptionEnum.UPLOAD_ERROR);
        }

        //step2:更新数据
        Integer userId = ShiroKit.getUser().getId();
        User currentUser = userService.selectByPK(userId);
        String avatar = currentUser.getAvatar();
        currentUser.setAvatar(pictureName);
        userService.updateAllColByPK(currentUser);

        //step3:删除旧图
        if (StringUtils.isNotBlank(avatar)) {
            String oldfile = fileSavePath + avatar;
            new File(oldfile).delete();
        }
        return pictureName;
    }

    /**
     * 删除管理员（逻辑删除）
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Tip delete(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能删除超级管理员
        if (userId.equals(AdminConst.ADMIN_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_DELETE_ADMIN);
        }
        this.userService.setStatus(userId, TrebleStatus.DELETED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 冻结用户
     */
    @RequestMapping("/freeze")
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Tip freeze(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能冻结超级管理员
        if (userId.equals(AdminConst.ADMIN_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_FREEZE_ADMIN);
        }
        this.userService.setStatus(userId, TrebleStatus.FREEZED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 解除冻结
     */
    @RequestMapping("/unfreeze")
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Tip unfreeze(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.userService.setStatus(userId, TrebleStatus.ACTIVED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 查看管理员详情
     */
    @RequestMapping("/view/{userId}")
    @ResponseBody
    public User view(@PathVariable Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userService.selectByPK(userId);
        ShiroKit.assertAuth(userId, user);
        return user;
    }

    /**
     * 重置密码
     */
    @RequestMapping("/reset")
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Tip reset(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        userService.resetPwd(userId);
        return SUCCESS_TIP;
    }

    /**
     * 跳转到角色分配页面
     */
    @RequestMapping("/role_assign/{userId}")
    public String roleAssign(@PathVariable Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userService.selectByPK(userId);
        model.addAttribute("userId", userId);
        model.addAttribute("userAccount", user.getAccount());
        return PREFIX + "role_assign";
    }

    /**
     * 分配角色
     */
    @RequestMapping("/setRole")
    @RequiresRoles(AdminConst.ADMIN_NAME)
    @ResponseBody
    public Tip setRole(@RequestParam("userId") Integer userId, @RequestParam("roleIds") String roleIds) {
        if (ToolUtil.isOneEmpty(userId, roleIds)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能修改超级管理员
        if (userId.equals(AdminConst.ADMIN_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_CHANGE_ADMIN);
        }
        //一般用户只具有几个角色，故不再设计桥接表
        this.userService.setRoles(userId, roleIds);
        return SUCCESS_TIP;
    }

}
