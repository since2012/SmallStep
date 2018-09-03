package org.tc.shiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描mapper接口(注意这里是tk包下的)
@MapperScan(basePackages = "org.tc.shiro.mapper")
//开启定时任务
//@EnableScheduling
//开启异步执行程序
//@EnableAsync
//扫描 所有需要的包, 包含一些自用的工具类包 所在的路径
@ComponentScan(basePackages = {"org.tc.shiro","com.stylefeng.guns.core"})
public class ShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class, args);
    }
}
