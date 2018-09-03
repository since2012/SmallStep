package org.tc.shiro.warpper;

import com.stylefeng.guns.core.util.ToolUtil;
import org.springframework.beans.BeanUtils;
import org.tc.mybatis.warpper.BeanWarpper;
import org.tc.shiro.core.common.constant.factory.ConstantFactory;
import org.tc.shiro.po.LoginLog;
import org.tc.shiro.vo.LoginLogVo;

/**
 * 日志列表的包装类
 *
 * @author fengshuonan
 * @date 2017年4月5日22:56:24
 */
public class LoginLogWarpper extends BeanWarpper<LoginLog, LoginLogVo> {
    @Override
    public LoginLogVo warpBean(LoginLog loginLog) {
        LoginLogVo vo = new LoginLogVo();
        BeanUtils.copyProperties(loginLog, vo);
        vo.setUserName(ConstantFactory.me().getUserNameById(loginLog.getUserid()));
        String message = loginLog.getMessage();
        //如果信息过长,则只截取前100位字符串
        if (ToolUtil.isNotEmpty(message) && message.length() >= 100) {
            String subMessage = message.substring(0, 100) + "...";
            vo.setMessage(subMessage);
        }
        //如果信息中包含分割符号;;;   则分割字符串返给前台
//        if (ToolUtil.isNotEmpty(message) && message.indexOf(Contrast.separator) != -1) {
//            String[] msgs = message.split(Contrast.separator);
//            vo.setRegularMessage(msgs);
//        } else {
//            vo.setRegularMessage(message);
//        }
        return vo;
    }

}
