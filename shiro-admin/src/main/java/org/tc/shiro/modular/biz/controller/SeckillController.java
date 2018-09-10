package org.tc.shiro.modular.biz.controller;


import com.github.pagehelper.PageInfo;
import com.stylefeng.guns.core.util.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tc.mybatis.controller.BaseController;
import org.tc.mybatis.dto.GlobalResult;
import org.tc.shiro.core.dto.ExecutionResult;
import org.tc.shiro.core.dto.Exposer;
import org.tc.shiro.core.dto.SeckillTimeData;
import org.tc.shiro.core.shiroext.kit.ShiroKit;
import org.tc.shiro.core.shiroext.vo.ShiroUser;
import org.tc.shiro.po.Seckill;
import org.tc.shiro.service.ISeckillService;

import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/seckill")
public class SeckillController extends BaseController {

    private String PREFIX = "biz/seckill/";

    @Autowired
    private ISeckillService seckillService;

    /**
     * 秒杀列表
     *
     * @return
     */
    @GetMapping("")
    public String index() {
        return PREFIX + "seckill";
    }

    /**
     * 秒杀列表
     *
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public Object list(Seckill seckill,
                       @RequestParam(required = false) Integer limit,
                       @RequestParam(required = false) Integer offset,
                       @RequestParam(required = false) String sort,
                       @RequestParam(required = false) String order) {
        if (StringUtils.isBlank(sort)) {
            sort = "name asc";
        } else {
            sort = sort + " " + order;
        }
        PageInfo<Seckill> page = seckillService.page(seckill, offset / limit + 1, limit, sort);
        return super.warpForBT(page.getList(), page.getTotal());
    }

    /**
     * 秒杀详情
     *
     * @param seckillId
     * @param model
     * @return
     */
    @GetMapping("/{seckillId}/detail")
    public String detial(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            //重定向：丢失数据
            return REDIRECT + "/seckill/list";
        }
        boolean exists = seckillService.existsByPK(seckillId);
        if (!exists) {
            //转发数据
            return FORWARD + "/seckill/list";
        }
        Seckill seckill = seckillService.selectByPK(seckillId);
        model.addAttribute("seckill", seckill);
        return PREFIX + "detail";
    }

    /**
     * 秒杀地址暴露
     *
     * @param seckillId
     * @return
     */
    @PostMapping("/{seckillId}/exposer")
    @ResponseBody
    public Object exposer(@PathVariable("seckillId") Long seckillId) {
        GlobalResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exoportSeckillUrl(seckillId);
            result = GlobalResult.ok(exposer);
        } catch (Exception e) {
            result = GlobalResult.errorMsg(e.getMessage());
        }
        return result;
    }


    @PostMapping("/{seckillId}/{md5}/execute")
    @ResponseBody
    public Object exec(@PathVariable("seckillId") Long seckillId,
                       @PathVariable("md5") String md5) {
        //未登录
        ShiroUser shiroUser = ShiroKit.getUser();
        if (ToolUtil.isEmpty(shiroUser)) {
            return GlobalResult.errorMsg("你还未登录");
        }
        ExecutionResult executionResult = null;
        executionResult = seckillService.executeSeckill(seckillId, md5);
        return GlobalResult.ok(executionResult);
    }

    /**
     * 执行秒杀(采用存储过程)
     *
     * @param seckillId
     * @param md5
     * @return
     */
    @PostMapping("/{seckillId}/{md5}/execution")
    @ResponseBody
    public Object execute(@PathVariable("seckillId") Long seckillId,
                          @PathVariable("md5") String md5) {
        //未登录
        ShiroUser shiroUser = ShiroKit.getUser();
        if (ToolUtil.isEmpty(shiroUser)) {
            return GlobalResult.errorMsg("你还未登录");
        }
        ExecutionResult execution = seckillService.executeSeckillProcedure(seckillId, md5);
        return GlobalResult.ok(execution);
    }

    @GetMapping("/{seckillId}/time")
    @ResponseBody
    public Object now(@PathVariable("seckillId") Long seckillId) {
        Date date = new Date();
        Seckill seckill = seckillService.selectByPK(seckillId);
        SeckillTimeData timeData = new SeckillTimeData(date.getTime(), seckill.getBegintime().getTime(), seckill.getEndtime().getTime());
        return GlobalResult.ok(timeData);
    }
}
