package org.tc.shiro.core.shiro.kit;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ShiroKit Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>08/20/2018</pre>
 */
@Slf4j
public class ShiroKitTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: md5(String credentials, String saltSource)
     */
    @Test
    public void testMd5() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]", ShiroKit.md5("111111", "admin"));
    }

    /**
     * Method: getSubject()
     */
    @Test
    public void testGetSubject() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: getUser()
     */
    @Test
    public void testGetUser() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: getSession()
     */
    @Test
    public void testGetSession() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: getSessionAttr(String key)
     */
    @Test
    public void testGetSetSessionAttr() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: removeSessionAttr(String key)
     */
    @Test
    public void testRemoveSessionAttr() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: hasRole(String roleName)
     */
    @Test
    public void testHasRole() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: lacksRole(String roleName)
     */
    @Test
    public void testLacksRole() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: hasAnyRoles(String roleNames)
     */
    @Test
    public void testHasAnyRoles() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: hasAllRoles(String roleNames)
     */
    @Test
    public void testHasAllRoles() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: hasPermission(String permission)
     */
    @Test
    public void testHasPermission() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: lacksPermission(String permission)
     */
    @Test
    public void testLacksPermission() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: isAuthenticated()
     */
    @Test
    public void testIsAuthenticated() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: notAuthenticated()
     */
    @Test
    public void testNotAuthenticated() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: isUser()
     */
    @Test
    public void testIsUser() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: isGuest()
     */
    @Test
    public void testIsGuest() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: principal()
     */
    @Test
    public void testPrincipal() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: isAdmin()
     */
    @Test
    public void testIsAdmin() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }

    /**
     * Method: isAjax(ServletRequest request)
     */
    @Test
    public void testIsAjax() throws Exception {
        //TODO: Test goes here... 
        log.info("[{}]");
    }


}
