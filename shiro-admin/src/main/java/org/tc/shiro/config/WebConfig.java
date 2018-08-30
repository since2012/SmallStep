package org.tc.shiro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.tc.fastjson.config.DefaultWebConfig;
import org.tc.shiro.core.interceptor.CharsetInterceptor;


/**
 * <p>
 * WEB 初始化相关配置
 * </p>
 *
 * @author hubin
 * @since 2017-12-11
 */
@Configuration
public class WebConfig extends DefaultWebConfig {

    /**
     * 注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CharsetInterceptor()).addPathPatterns("/**");
//        HandlerInterceptor handlerInterceptor = new HandlerInterceptor(){...}
//        registry.addInterceptor(handlerInterceptor).addPathPatterns("/**");
    }

    /**
     * 指定静态资源目录
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
        super.addResourceHandlers(registry);
    }

}
