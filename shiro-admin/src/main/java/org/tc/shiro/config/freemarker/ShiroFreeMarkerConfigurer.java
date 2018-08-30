package org.tc.shiro.config.freemarker;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;

/**
 * 继承FreeMarkerConfigurer类,重写afterPropertiesSet()方法；
 * 集成shiroTags标签
 */
public class ShiroFreeMarkerConfigurer extends FreeMarkerConfigurer {

    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        Configuration configuration = this.getConfiguration();
        //！！！重要：使用shiro标签
        configuration.setSharedVariable("shiro", new ShiroTags());
        //防止页面输出数字,变成2,000
        configuration.setNumberFormat("#");
        //可以添加很多自己的要传输到页面的[方法、对象、值]
        configuration.setDefaultEncoding("UTF-8");
//        configuration.setEncoding(Locale.CHINA, "UTF-8");
        configuration.setDateFormat("yyyy-MM-dd");
        configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
    }
}

