//package org.tan.shiro.mapper;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.List;
//
///**
// * Created by Liwei on 2016/9/18.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring/spring-dao.xml"})
//public class TUserDaoTest {
//
//    private static final Logger logger = LoggerFactory.getLogger(TUserDaoTest.class);
//
//    @Autowired
//    private UserDao userDao;
//
//    @Test
//    public void testAdd() throws Exception {
//        SysUser SysUser = new SysUser();
//        SysUser.setUsername("zhouguang");
//        SysUser.setPassword("666666");
//        SysUser.setNickname("周光");
//        SysUser.setCode(1);
//        Integer insertNum = userDao.add(SysUser);
//        logger.debug(" insertNum => " + insertNum);
//        logger.debug("返回自增长的主键：" + SysUser.getId());
//    }
//
//    @Test
//    public void testListUser() throws Exception {
//        List<SysUser> TUsers = userDao.listUser();
//        for(SysUser SysUser : TUsers){
//            logger.debug(SysUser.toString());
//        }
//    }
//
//    @Test
//    public void testLoadByUserName() throws Exception {
//        SysUser SysUser = userDao.loadByUserName("liwei");
//        logger.debug(SysUser.toString());
//
//    }
//
//    /**
//     * 根据角色 id 查询是该角色的所有用户
//     * @throws Exception
//     */
//    @Test
//    public void testListByRole() throws Exception {
//        List<SysUser> TUsers = userDao.listByRole(2);
//        for(SysUser SysUser : TUsers){
//            logger.debug(SysUser.toString());
//        }
//    }
//
//    @Test
//    public void testListAllResources() throws Exception {
//        Integer uid = 1;
//        List<Resource> resources = userDao.listAllResources(uid);
//        for(Resource resource:resources){
//            logger.debug(resource.toString());
//        }
//    }
//
//    @Test
//    public void testListRoleSnByUser() throws Exception {
//        List<String> sns = userDao.listRoleSnByUser(1);
//        logger.debug(sns.toString());
//    }
//
//    @Test
//    public void testListUserRole() throws Exception {
//        List<Role> roles = userDao.listUserRole(1);
//        for(Role role:roles){
//            System.out.println(role.toString());
//        }
//    }
//}