package org.tc.shiro.modular.system.controller;

import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tc.mybatis.controller.BaseController;
import org.tc.mybatis.exception.GunsException;
import org.tc.redis.cache.RedisCacheDao;
import org.tc.shiro.core.common.constant.cache.Cache;
import org.tc.shiro.core.common.constant.cache.CacheKey;
import org.tc.shiro.core.common.constant.factory.ConstantFactory;
import org.tc.shiro.core.common.exception.BizExceptionEnum;
import org.tc.shiro.modular.system.service.IDeptService;
import org.tc.shiro.po.Dept;
import org.tc.shiro.warpper.DeptWarpper;

import javax.validation.Valid;
import java.util.List;

/**
 * 部门控制器
 *
 * @author fengshuonan
 * @Date 2017年2月17日20:27:22
 */
@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {

    private String PREFIX = "system/dept/";

    @Autowired
    private IDeptService deptService;
    @Autowired
    private RedisCacheDao redisCacheDao;

    /**
     * 跳转到部门管理首页
     */
    @GetMapping("")
    public String index() {
        return PREFIX + "dept";
    }

    /**
     * 获取所有部门列表
     */
    @PostMapping("/list")
    @ResponseBody
    public Object list(String name, String tips) {
        List<Dept> list = this.deptService.list(name, tips);
        return new DeptWarpper().warpList(list);
    }

    /**
     * 跳转到添加部门
     */
    @GetMapping("/add")
    public String deptAdd() {
        return PREFIX + "add";
    }

    /**
     * 新增部门
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid Dept dept, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //完善pids,根据pid拿到pid的pids
        setPids(dept);
        this.deptService.insertAllCol(dept);
        return SUCCESS_TIP;
    }

    /**
     * 跳转到修改部门
     */
    @GetMapping("/edit/{deptId}")
    public String deptUpdate(@PathVariable Integer deptId, Model model) {
        Dept dept = deptService.selectByPK(deptId);
        model.addAttribute("dept", dept);
        model.addAttribute("pName", ConstantFactory.me().getDeptName(dept.getPid()));
        return PREFIX + "edit";
    }

    /**
     * 修改部门
     */
    @PostMapping("/update")
    @ResponseBody
    public Object update(Dept dept) {
        if (ToolUtil.isEmpty(dept) || dept.getId() == null) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        setPids(dept);
        deptService.updateByPKSelective(dept);
        return SUCCESS_TIP;
    }

    /**
     * 删除部门
     */
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer deptId) {
        //缓存被删除的部门名称
        Dept oldDept = deptService.selectByPK(deptId);
        redisCacheDao.put(Cache.CRUD, CacheKey.PO_BEFORE + deptId, oldDept);
        deptService.deleteCascade(deptId);
        return SUCCESS_TIP;
    }

//    /**
//     * 部门详情
//     */
//    @RequestMapping( "/detail/{deptId}")
//    @ResponseBody
//    public Object detail(@PathVariable("deptId") Integer deptId) {
//        return deptService.selectByPK(deptId);
//    }

    /**
     * 获取部门的tree列表
     */
    @PostMapping("/tree")
    @ResponseBody
    public List<ZTreeNode> tree() {
        List<ZTreeNode> tree = this.deptService.tree();
        tree.add(ZTreeNode.createRoot());
        return tree;
    }

    /**
     * 配置PIDS
     *
     * @param dept
     */
    private void setPids(Dept dept) {
        Integer pid = dept.getPid();
        if (ToolUtil.isEmpty(pid) || pid.equals(0)) {
            dept.setPid(0);
            dept.setPids("[0],");
        } else {
            Dept temp = deptService.selectByPK(pid);
            String pids = temp.getPids();
            dept.setPids(pids + "[" + pid + "],");
        }
    }
}
