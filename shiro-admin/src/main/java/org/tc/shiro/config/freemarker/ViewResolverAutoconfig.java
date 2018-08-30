package org.tc.shiro.config.freemarker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Properties;

/**
 * 视图解析配置
 */
@Configuration
public class ViewResolverAutoconfig {

    @Autowired
    private FreeMarkerProperties properties;

    /**
     * 注册freemarker配置信息
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean({FreeMarkerConfig.class})
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer configurer = new ShiroFreeMarkerConfigurer();
        applyProperties(configurer);
        return configurer;
    }

    public void applyProperties(FreeMarkerConfigurationFactory factory) {
        factory.setTemplateLoaderPaths(this.properties.getTemplateLoaderPath());
        factory.setPreferFileSystemAccess(this.properties.isPreferFileSystemAccess());
        factory.setDefaultEncoding(this.properties.getCharsetName());
        Properties settings = new Properties();
        settings.putAll(this.properties.getSettings());
        factory.setFreemarkerSettings(settings);
    }

}
