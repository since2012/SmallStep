//package org.tan.shiro.service.impl;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.tan.shiro.service.IUserService;
//
///**
// * Created by liwei on 16/9/25.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {
//        "classpath:spring/spring-service.xml",
//        "classpath:spring/spring-dao.xml",
//        "classpath:spring/spring-shiro.xml"})
//public class TUserServiceTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(TUserServiceTest.class);
//
//    @Autowired
//    private IUserService userService;
//
//
//    @Test
//    public void add() throws Exception {
//        SysUser SysUser = new SysUser();
//        SysUser.setUsername("zhouguang");
//        SysUser.setPassword("666666");
//        SysUser.setNickname("周光1");
//        SysUser.setCode(1);
//        userService.add(SysUser);
//        logger.debug("返回自增长的主键：" + SysUser.getId());
//    }
//
//}