package org.tc.shiro.core.shiroext.filter;

import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.tc.mybatis.util.HttpKit;
import org.tc.shiro.core.log.LogManager;
import org.tc.shiro.core.log.factory.LogTaskFactory;
import org.tc.shiro.core.shiroext.kit.ShiroKit;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义登出过滤器
 */
public class MyLogoutFilter extends LogoutFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        //登出日志
        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUser().getId(), HttpKit.getIp()));
        ShiroKit.getSubject().logout();
        return super.preHandle(request, response);
    }
}
