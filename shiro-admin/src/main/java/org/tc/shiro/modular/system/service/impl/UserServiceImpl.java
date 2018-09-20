package org.tc.shiro.modular.system.service.impl;

import com.stylefeng.guns.core.util.ToolUtil;
import org.springframework.stereotype.Service;
import org.tc.mybatis.exception.GunsException;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.core.common.constant.AdminConst;
import org.tc.shiro.core.common.constant.enums.TrebleStatus;
import org.tc.shiro.core.common.exception.BizExceptionEnum;
import org.tc.shiro.core.shiroext.kit.ShiroKit;
import org.tc.shiro.mapper.UserMapper;
import org.tc.shiro.modular.system.service.IUserService;
import org.tc.shiro.po.User;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-02-22
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

    /**
     * 给用户配置新的密码和盐
     *
     * @param user
     * @param password 新密码
     * @return
     */
    private User setPwd(User user, String password) {
        String randomSalt = ShiroKit.getRandomSalt(5);
        String newMd5 = ShiroKit.md5(password, randomSalt);
        user.setSalt(randomSalt);
        user.setPassword(newMd5);
        return user;
    }

    @Override
    public void add(User user) {
        boolean exists = baseMapper.existsByAccount(user.getAccount());
        if (exists) {
            throw new GunsException(BizExceptionEnum.USER_ALREADY_REG);
        }
        user = setPwd(user, user.getPassword());
        //防止篡改数据（恶意获取权限）
        user.setRoleid(null);
        user.setStatus(TrebleStatus.ACTIVED.getCode());
        user.setCreatetime(new Date());
        this.baseMapper.insertUseGeneratedKeys(user);
    }


    /**
     * 将可变更的数据复制给旧数据
     *
     * @param newUser
     * @param oldUser
     * @return
     */
    private User copyData(User newUser, User oldUser) {
        if (newUser == null || oldUser == null) {
            return oldUser;
        } else {
            if (ToolUtil.isNotEmpty(newUser.getName())) {
                oldUser.setName(newUser.getName());
            }
            if (ToolUtil.isNotEmpty(newUser.getBirthday())) {
                oldUser.setBirthday(newUser.getBirthday());
            }
            if (ToolUtil.isNotEmpty(newUser.getDeptid())) {
                oldUser.setDeptid(newUser.getDeptid());
            }
            if (ToolUtil.isNotEmpty(newUser.getSex())) {
                oldUser.setSex(newUser.getSex());
            }
            if (ToolUtil.isNotEmpty(newUser.getEmail())) {
                oldUser.setEmail(newUser.getEmail());
            }
            if (ToolUtil.isNotEmpty(newUser.getPhone())) {
                oldUser.setPhone(newUser.getPhone());
            }
            return oldUser;
        }
    }

    @Override
    public boolean edit(User newUser, User oldUser) {
        User user = copyData(newUser, oldUser);
        int result = baseMapper.updateByPrimaryKey(user);
        return retBool(result);
    }

    @Override
    public void changePwd(String oldPwd, String newPwd, String rePwd) {
        if (!newPwd.equals(rePwd)) {
            throw new GunsException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
        }
        Integer userId = ShiroKit.getUser().getId();
        User currentUser = baseMapper.selectByPrimaryKey(userId);
        String prevSalt = currentUser.getSalt();

        String oldMd5 = ShiroKit.md5(oldPwd, prevSalt);
        if (currentUser.getPassword().equals(oldMd5)) {
            baseMapper.updateByPrimaryKey(setPwd(currentUser, newPwd));
        } else {
            throw new GunsException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
        }
    }


    @Override
    public void resetPwd(Integer id) {
        User user = this.baseMapper.selectByPrimaryKey(id);
        ShiroKit.assertAuth(id, user);
        baseMapper.updateByPrimaryKey(setPwd(user, AdminConst.DEFAULT_PWD));
    }


    @Override
    public void setStatus(Integer userId, int status) {
        User user = baseMapper.selectByPrimaryKey(userId);
        ShiroKit.assertAuth(userId, user);
        user.setStatus(status);
        baseMapper.updateByPrimaryKey(user);
    }

    @Override
    public void setRoles(Integer userId, String roleIds) {
        User user = baseMapper.selectByPrimaryKey(userId);
        ShiroKit.assertAuth(userId, user);
        user.setRoleid(roleIds);
        baseMapper.updateByPrimaryKey(user);
    }

    @Override
    public List<User> list(User user, String beginTime, String endTime) {
        if (!ShiroKit.isAdmin()) {
            List<Integer> deptDataScope = ShiroKit.getDeptDataScope();
            return baseMapper.list(deptDataScope, user, beginTime, endTime);
        }
        return baseMapper.list(null, user, beginTime, endTime);
    }

}
