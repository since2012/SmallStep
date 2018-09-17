# 基于Guns V4.0
运行效果见于 "运行效果图.doc"

## 介绍
基于开源项目Guns,整合springboot + shiro + mybatis + spring-data-redis + freemarker+AdminLTE! 
Guns包含许多基础模块(用户管理,角色管理,部门管理,字典管理等模块),可以直接作为一个后台管理系统的脚手架! 

## 开发环境
```
IntelliJ IDEA: 2017.3
Java version: 1.8.0_152
Apache Maven: 3.5.2
Your MySQL Server version: 5.7.20 MySQL Community Server (GPL)
SpringBoot: 2.0.3.RELEASE
Apache Shiro: 1.4.0
Redis server v=3.2.100
```

## 管理系统功能
1.用户管理 2.角色管理 3.部门管理 4.菜单管理 5.字典管理 6.业务日志 7.登录日志 8.监控管理 9.通知管理 
10.代码生成(前端生成较为鸡肋故移除，修改为后台一次性生成，根据需要复制到项目中重构后使用)

## 主要工作

> * 将项目前端模板替换AdminLTE,整合原有前端插件及工具
> * 提取出通用的工具包tool-jar-springboot，见于我的其他项目
> * 将持久层的框架替换为mybatis+tk.mapper+pagehelper，自定义了父接口MyMapper,service层的IBaseService和BaseServiceImpl
> * 将一下个人项目整合其中：仿微信公众号的自动回复；秒杀业务模拟；商户发放优惠券（二维码业务场景）
> * 采用自定义的shiro过滤器控制访问（删减了部分硬编码代码）
> * 选取protostuff作为redis的序列化方案并整合到redis中
> * 选取spring-boot-starter-data-redis作为整个项目的缓存
> * 整合redis作为shiro的缓存
> * 废弃了map + warpper方案，将处理过后的PO数据保存到VO对象，再返回给前端
> * 采用重新打包kaptcha-spring-boot-starter作为验证码生成方案
> * 版本升级：将spring boot升级到2.0.3版本,并升级各个依赖包的版本！
> * 采用javax.validation作为后台表单验证的工具

## 将redis作为windows服务安装
```
1,下载redis并解压到一个目录下，然后切换到该目录下，也就是redis-server.exe文件所在的目录
2,在cmd下执行 redis-server --service-install redis.windows.conf
3,服务安装成功后启动服务 redis-server --service-start
卸载命令：
 redis-server --service-uninstall  
```

## 运行
> * http://localhost:8080/
> * 用户名：admin
> * 密码：111111

## 结语
匆忙完成,难免有纰漏之处