package org.tc.shiro.warpper;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.tc.mybatis.warpper.BeanWarpper;
import org.tc.shiro.core.common.constant.factory.ConstantFactory;
import org.tc.shiro.core.common.constant.factory.IConstantFactory;
import org.tc.shiro.po.Cmd;
import org.tc.shiro.vo.CmdVo;

/**
 * 用户管理的包装类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:47:03
 */
@Data
public class CmdWarpper extends BeanWarpper<Cmd, CmdVo> {

    @Override
    public CmdVo warpBean(Cmd po) {
        CmdVo cmdVo = new CmdVo();
        BeanUtils.copyProperties(po, cmdVo);
        IConstantFactory factory = ConstantFactory.me();
        cmdVo.setContents(factory.selectByCmdId(po.getId()));
        return cmdVo;
    }

}
