package org.tc.shiro.core.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

/**
 * 在此配置需要装饰的页面
 */
public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/mgr", "/decorator/main")
                .addDecoratorPath("/role", "/decorator/main")
                .addDecoratorPath("/menu", "/decorator/main")
                .addDecoratorPath("/dict", "/decorator/main")
                .addDecoratorPath("/dept", "/decorator/main")
                .addDecoratorPath("/index", "/decorator/main")
                .addDecoratorPath("/blackboard", "/decorator/main")
                .addDecoratorPath("/notice", "/decorator/main")
                .addDecoratorPath("/login_log", "/decorator/main")
                .addDecoratorPath("/mgr/profile", "/decorator/main")
                .addDecoratorPath("/cmd", "/decorator/main")
                .addDecoratorPath("/robot", "/decorator/main")
                .addDecoratorPath("/seller", "/decorator/main")
                .addDecoratorPath("/coupon", "/decorator/main")
                .addDecoratorPath("/stock", "/decorator/main")
                .addDecoratorPath("/stock/*/seckill", "/decorator/main")
                .addDecoratorPath("/stock/show", "/decorator/main");
    }
}