package org.tc.shiro.warpper;

import com.stylefeng.guns.core.util.ToolUtil;
import org.springframework.beans.BeanUtils;
import org.tc.mybatis.warpper.BeanWarpper;
import org.tc.shiro.core.common.constant.factory.ConstantFactory;
import org.tc.shiro.po.Dept;
import org.tc.shiro.vo.DeptVo;

/**
 * 部门列表的包装
 *
 * @author fengshuonan
 * @date 2017年4月25日 18:10:31
 */
public class DeptWarpper extends BeanWarpper<Dept, DeptVo> {


    @Override
    public DeptVo warpBean(Dept po) {
        DeptVo vo = new DeptVo();
        BeanUtils.copyProperties(po, vo);
        Integer pid = po.getPid();
        if (ToolUtil.isEmpty(pid) || pid.equals(0)) {
            vo.setPName("--");
        } else {
            vo.setPName(ConstantFactory.me().getDeptName(pid));
        }
        return vo;
    }

}
