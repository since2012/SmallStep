package org.tc.shiro.core.log.factory;

import com.stylefeng.guns.core.db.Db;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tc.shiro.core.common.constant.enums.LogType;
import org.tc.shiro.core.log.LogManager;
import org.tc.shiro.mapper.LoginLogMapper;
import org.tc.shiro.po.LoginLog;

import java.util.TimerTask;

/**
 * 日志操作任务创建工厂
 *
 * @author fengshuonan
 * @date 2016年12月6日 下午9:18:27
 */
public class LogTaskFactory {

    private static Logger logger = LoggerFactory.getLogger(LogManager.class);
    private static LoginLogMapper loginLogMapper = Db.getMapper(LoginLogMapper.class);

    /**
     * 登录成功日志
     *
     * @param userId
     * @param ip
     * @return
     */
    public static TimerTask loginLog(final Integer userId, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                try {
                    LoginLog loginLog = LogFactory.createLoginLog(LogType.LOGIN, userId, "登录成功", ip);
                    loginLogMapper.insertUseGeneratedKeys(loginLog);
                } catch (Exception e) {
                    logger.error("创建登录日志异常!", e);
                }
            }
        };
    }

    /**
     * 登录失败日志
     *
     * @param username
     * @param msg
     * @param ip
     * @return
     */
    public static TimerTask loginLog(final String username, final String msg, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog(
                        LogType.LOGIN_FAIL, null, "账号:" + username + "," + msg, ip);
                try {
                    loginLogMapper.insertUseGeneratedKeys(loginLog);
                } catch (Exception e) {
                    logger.error("创建登录失败异常!", e);
                }
            }
        };
    }

    /**
     * 退出日志
     *
     * @param userId
     * @param ip
     * @return
     */
    public static TimerTask exitLog(final Integer userId, final String ip) {
        return new TimerTask() {
            @Override
            public void run() {
                LoginLog loginLog = LogFactory.createLoginLog(LogType.EXIT, userId, null, ip);
                try {
                    loginLogMapper.insertUseGeneratedKeys(loginLog);
                } catch (Exception e) {
                    logger.error("创建退出日志异常!", e);
                }
            }
        };
    }

}
