package org.tc.shiro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class FilterConfig {

    /**
     * 过滤器注册对象
     *
     * @return
     */
//    @Bean
//    public FilterRegistrationBean characterEncodingFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        //注入过滤器
//        registration.setFilter(characterEncodingFilter());
//        //拦截规则
//        registration.addUrlPatterns("/*");
//        //过滤器名称
//        registration.setName("CharacterEncodingFilter");
//        //过滤器顺序
//        registration.setOrder(10);
//        return registration;
//    }

    /**
     * 字符过滤器生成
     *
     * @return
     */
    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

}
