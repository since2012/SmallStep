package org.tc.shiro.warpper;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.tc.mybatis.warpper.BeanWarpper;
import org.tc.shiro.core.common.constant.factory.ConstantFactory;
import org.tc.shiro.core.common.constant.factory.IConstantFactory;
import org.tc.shiro.po.User;
import org.tc.shiro.vo.UserVo;

/**
 * 用户管理的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
@Data
public class UserWarpper extends BeanWarpper<User, UserVo> {

    @Override
    public UserVo warpBean(User po) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(po, userVo);
        IConstantFactory factory = ConstantFactory.me();
        userVo.setSexName(factory.getSexName(po.getSex()));
        userVo.setRoleName(factory.getMultiRoleName(po.getRoleid()));
        userVo.setDeptName(factory.getDeptName(po.getDeptid()));
        userVo.setStatusName(factory.getStatusName(po.getStatus()));
        return userVo;
    }

}
