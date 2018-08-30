package org.tc.shiro.config;

import com.baomidou.kaptcha.Kaptcha;
import com.baomidou.kaptcha.spring.boot.KaptchaAutoConfiguration;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.tc.shiro.core.shiro.cache.ShiroSpringCacheManager;
import org.tc.shiro.core.shiro.filter.GunsUserFilter;
import org.tc.shiro.core.shiro.filter.KaptchaFilter;
import org.tc.shiro.core.shiro.kit.ShiroKit;
import org.tc.shiro.core.shiro.permission.AntPermissionResolver;
import org.tc.shiro.core.shiro.realm.ShiroDbRealm;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro权限管理的配置
 *
 * @author fengshuonan
 * @date 2016年11月14日 下午3:03:44
 */
@Configuration
@AutoConfigureAfter(KaptchaAutoConfiguration.class)
public class ShiroConfig {

    /**
     * 解决UnavailableSecurityManagerException
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean delegatingFilterProxy() {
        //过滤器注册对象
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        registrationBean.setFilter(proxy);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(-1);
        return registrationBean;
    }

    /**
     * Shiro的过滤器链
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager,
                                              Kaptcha kaptcha) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        String loginUrl = "/login";
        shiroFilter.setLoginUrl(loginUrl);
        shiroFilter.setSuccessUrl("/admin/user/list");
        shiroFilter.setUnauthorizedUrl("/unauthorized");

        /**
         * 覆盖默认的user拦截器(默认拦截器解决不了ajax请求 session超时的问题,若有更好的办法请及时反馈作者)
         */
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        filtersMap.put("gunsFilter", new GunsUserFilter(loginUrl));
        KaptchaFilter kaptchaFilter = new KaptchaFilter(kaptcha, 180);
        filtersMap.put("kaptchaFilter", kaptchaFilter);
        LogoutFilter logout = new LogoutFilter();
        logout.setRedirectUrl("/login");
        filtersMap.put("logout", logout);
        shiroFilter.setFilters(filtersMap);

        // 拦截器.
        //rest：比如/admins/user/**=rest[user],根据请求的方法，相当于/admins/user/**=perms[user：method] ,其中method为post，get，delete等。
        //port：比如/admins/user/**=port[8081],当请求的url的端口不是8081是跳转到schemal：//serverName：8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString是你访问的url里的？后面的参数。
        //perms：比如/admins/user/**=perms[user：tip：*],perms参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，比如/admins/user/**=perms["user：tip：*,user：tip：*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
        //roles：比如/admins/user/**=roles[admin],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，比如/admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。//要实现or的效果看http://zgzty.blog.163.com/blog/static/83831226201302983358670/
        //anon：比如/admins/**=anon 没有参数，表示可以匿名使用。
        //authc：比如/admins/user/**=authc表示需要认证才能使用，没有参数
        //authcBasic：比如/admins/user/**=authcBasic没有参数表示httpBasic认证
        //ssl：比如/admins/user/**=ssl没有参数，表示安全的url请求，协议为https
        //user：比如/admins/user/**=user没有参数表示必须存在用户，当登入操作时不做检查
        LinkedHashMap<String, String> hashMap = new LinkedHashMap<>();
        //表示需要认证才可以访问
        hashMap.put("/login", "kaptchaFilter");
        hashMap.put("/logout", "logout");
        hashMap.put("/kaptcha", "anon");

        hashMap.put("/static/**", "anon");
        hashMap.put("/js/**", "anon");
        hashMap.put("/css/**", "anon");
        hashMap.put("/images/**", "anon");

        hashMap.put("/**", "gunsFilter");
        //这段是配合 actuator框架使用的，配置相应的角色才能访问
//        filterChainDefinitionMap.put("/health", "roles[aix]");//服务器健康状况页面
// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        shiroFilter.setFilterChainDefinitionMap(hashMap);
        return shiroFilter;
    }

    /**
     * 安全管理器
     */
    @Bean
    public DefaultWebSecurityManager securityManager(ShiroSpringCacheManager cacheManager,
                                                     WebSessionManager sessionManager,
                                                     ShiroDbRealm shiroDbRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroDbRealm);
        securityManager.setCacheManager(cacheManager);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }

    /**
     * 配置自定义的权限登录器
     *
     * @param matcher
     * @param antPermissionResolver
     * @return
     */
    @Bean
    public ShiroDbRealm shiroDbRealm(CredentialsMatcher matcher, AntPermissionResolver antPermissionResolver) {
        ShiroDbRealm authRealm = new ShiroDbRealm();
        //开启缓存
        authRealm.setAuthenticationCachingEnabled(true);
        authRealm.setCredentialsMatcher(matcher);
        authRealm.setPermissionResolver(antPermissionResolver);
        return authRealm;
    }

    /**
     * 配置自定义的密码比较器
     *
     * @return
     */
    @Bean("credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher(ShiroKit.hashAlgorithmName);
        hashedCredentialsMatcher.setHashIterations(ShiroKit.hashIterations);
        return hashedCredentialsMatcher;
    }

    /**
     * 自定义的地址访问控制
     *
     * @return
     */
    @Bean("antPermissionResolver")
    public AntPermissionResolver antPermissionResolver() {
        return new AntPermissionResolver();
    }

    /**
     * spring session管理器（多机环境）
     */
    @Bean
    public ServletContainerSessionManager servletContainerSessionManager() {
        return new ServletContainerSessionManager();
    }

    @Bean
    public CachingSessionDAO sessionDAO(ShiroSpringCacheManager cacheManager) {
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setCacheManager(cacheManager);
        return sessionDAO;
    }

    /**
     * 整合redis作为缓存
     *
     * @param cacheManager
     * @return
     */
    @Bean
    public ShiroSpringCacheManager shiroSpringCacheManager(CacheManager cacheManager) {
        ShiroSpringCacheManager shiroSpringCacheManager = new ShiroSpringCacheManager(cacheManager);
        return shiroSpringCacheManager;
    }

    /**
     * Shiro生命周期处理器:
     * 用于在实现了Initializable接口的Shiro bean初始化时调用Initializable接口回调(例如:UserRealm)
     * 在实现了Destroyable接口的Shiro bean销毁时调用 Destroyable接口回调(例如:DefaultSecurityManager)
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 在方法中 注入 securityManager,进行代理控制
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultWebSecurityManager securityManager) {
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        bean.setArguments(new Object[]{securityManager});
        return bean;
    }

    /**
     * 启用shrio授权注解拦截方式，AOP式方法级权限检查
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
                new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


    /**
     * rememberMe管理器, cipherKey生成见{@code Base64Test.java}
     */
//    @Bean
//    @ConditionalOnProperty(prefix = "guns", name = "remember-me-open:", havingValue = "true")
//    public CookieRememberMeManager rememberMeManager() {
//        CookieRememberMeManager manager = new CookieRememberMeManager();
//        manager.setCipherKey(Base64.decode("Z3VucwAAAAAAAAAAAAAAAA=="));
//        manager.setCookie(rememberMeCookie());
//        return manager;
//    }

    /**
     * 记住密码Cookie
     */
//    @Bean
//    @ConditionalOnProperty(prefix = "guns", name = "remember-me-open:", havingValue = "true")
//    public SimpleCookie rememberMeCookie() {
//        SimpleCookie simpleCookie = new SimpleCookie("rememberMeOpen");
//        simpleCookie.setHttpOnly(true);
//        simpleCookie.setMaxAge(7 * 24 * 60 * 60);//7天
//        return simpleCookie;
//    }
}

