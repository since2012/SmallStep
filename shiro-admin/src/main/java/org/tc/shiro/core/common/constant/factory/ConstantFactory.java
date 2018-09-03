package org.tc.shiro.core.common.constant.factory;

import com.stylefeng.guns.core.support.CollectionKit;
import com.stylefeng.guns.core.support.StrKit;
import com.stylefeng.guns.core.util.SpringContextHolder;
import com.stylefeng.guns.core.util.ToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.tc.shiro.core.common.constant.cache.Cache;
import org.tc.shiro.core.common.constant.cache.CacheKey;
import org.tc.shiro.core.common.constant.enums.TrebleStatus;
import org.tc.shiro.mapper.*;
import org.tc.shiro.po.Dept;
import org.tc.shiro.po.Dict;
import org.tc.shiro.po.Role;
import org.tc.shiro.po.User;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * 常量的生产工厂
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:55:21
 */
@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {

    /**
     * 获取mapper
     */
    private RoleMapper roleMapper = SpringContextHolder.getBean(RoleMapper.class);
    private DeptMapper deptMapper = SpringContextHolder.getBean(DeptMapper.class);
    private DictMapper dictMapper = SpringContextHolder.getBean(DictMapper.class);
    private UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
    private MenuMapper menuMapper = SpringContextHolder.getBean(MenuMapper.class);

    public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }

    @Override
    public String getUserNameById(Integer userId) {
        if (userId == null) {
            return "--";
        }
        try {
            User user = userMapper.selectByPrimaryKey(userId);
            return user.getName();
        } catch (EntityNotFoundException e) {
            return "--";
        }
    }

    @Override
    public String getUserAccountById(Integer userId) {
        if (userId == null) {
            return "--";
        }
        try {
            User user = userMapper.selectByPrimaryKey(userId);
            return user.getAccount();
        } catch (EntityNotFoundException e) {
            return "--";
        }
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.ROLES_NAME + "'+#roleids")
    public String getMultiRoleName(String roleids) {
        if (StringUtils.isBlank(roleids)) {
            return "--";
        }
        List<String> split = StrKit.split(roleids, ',');
        List<String> roleNameList = roleMapper.nameList(split);
        return CollectionKit.join(roleNameList, ",");
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_NAME + "'+#roleid")
    public String getSingleRoleName(Integer roleid) {
        if (roleid == null || 0 == roleid) {
            return "--";
        }
        Role roleObj = roleMapper.selectByPrimaryKey(roleid);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getName();
        }
        return "";
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.SINGLE_ROLE_TIP + "'+#roleid")
    public String getRoleEnName(Integer roleid) {
        if (roleid == null || 0 == roleid) {
            return "--";
        }
        Role roleObj = roleMapper.selectByPrimaryKey(roleid);
        if (ToolUtil.isNotEmpty(roleObj) && ToolUtil.isNotEmpty(roleObj.getName())) {
            return roleObj.getTips();
        }
        return "--";
    }

    @Override
    @Cacheable(value = Cache.CONSTANT, key = "'" + CacheKey.DEPT_NAME + "'+#deptId")
    public String getDeptName(Integer deptId) {
        if (deptId == null || deptId == 0) {
            return "--";
        }
        Dept dept = deptMapper.selectByPrimaryKey(deptId);
        if (ToolUtil.isNotEmpty(dept) && ToolUtil.isNotEmpty(dept.getFullname())) {
            return dept.getFullname();
        }
        return "--";
    }


    @Override
    public List<Integer> getSubDeptId(Integer deptId) {
        if (deptId == null) {
            return null;
        }
        List<Integer> list = this.deptMapper.subDeptIdList("%[" + deptId + "]%");
        return list;
    }

    @Override
    public List<Integer> getParentDeptIds(Integer deptId) {
        if (deptId == null) {
            return null;
        }
        Dept dept = deptMapper.selectByPrimaryKey(deptId);
        String pids = dept.getPids();
        String[] split = pids.split(",");
        ArrayList<Integer> parentDeptIds = new ArrayList<>();
        for (String s : split) {
            parentDeptIds.add(Integer.valueOf(StrKit.removeSuffix(StrKit.removePrefix(s, "["), "]")));
        }
        return parentDeptIds;
    }

    @Override
    public String getMenuNames(String menuids) {
        if (StringUtils.isBlank(menuids)) {
            return "--";
        }
        List<String> list = menuMapper.nameList(menuids);
        return CollectionKit.join(list, ",");
    }

    @Override
    public String getDictName(Integer dictId) {
        if (ToolUtil.isEmpty(dictId)) {
            return "";
        }
        try {
            Dict dict = dictMapper.selectByPrimaryKey(dictId);
            return dict.getName();
        } catch (EntityNotFoundException e) {
            return "";
        }
    }

    @Override
    public String getDictsByName(String name, Integer val) {
        Dict dict = dictMapper.findByNameEquals(name);
        if (dict != null) {
            List<Dict> dicts = dictMapper.findByPidEquals(dict.getId());
            for (Dict item : dicts) {
                if (item.getNum() != null && item.getNum().equals(val)) {
                    return item.getName();
                }
            }
        }
        return "";
    }

    /**
     * 获取性别名称
     */
    @Override
    public String getSexName(Integer sex) {
        return getDictsByName("性别", sex);
    }

    @Override
    public String getStatusName(Integer status) {
        return TrebleStatus.valueOf(status);
    }

    @Override
    public List<Dict> findChildrenInDict(Integer id) {
        if (ToolUtil.isEmpty(id)) {
            return null;
        }
        List<Dict> dicts = dictMapper.findByPidEquals(id);
        return dicts;
    }

}
