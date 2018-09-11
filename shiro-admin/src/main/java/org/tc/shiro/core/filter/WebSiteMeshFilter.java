package org.tc.shiro.core.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/mgr", "/decorator/main")
                .addDecoratorPath("/role", "/decorator/main")
                .addDecoratorPath("/menu", "/decorator/main")
                .addDecoratorPath("/dict", "/decorator/main")
                .addDecoratorPath("/dept", "/decorator/main")
                .addDecoratorPath("/login_log", "/decorator/main")
                .addDecoratorPath("/mgr/profile", "/decorator/main")
                .addDecoratorPath("/cmd", "/decorator/main")
                .addDecoratorPath("/robot", "/decorator/main")
                .addDecoratorPath("/stock", "/decorator/main")
                .addDecoratorPath("/stock/*/seckill", "/decorator/main")
                .addDecoratorPath("/druid/*", "/decorator/main");
    }
}