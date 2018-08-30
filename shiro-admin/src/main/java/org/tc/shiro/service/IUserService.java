package org.tc.shiro.service;

import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.SysUser;

import java.util.List;

/**
 * Created by Liwei on 2016/9/18.
 */
public interface IUserService extends IBaseService<SysUser> {

    /**
     * 初始化用户信息
     *
     * @param roleIdList
     * @return
     */
    public boolean insert(SysUser sysUser, List<Byte> roleIdList);

    /**
     * 给用户重新分配角色
     *
     * @param userId
     * @param roleIdList
     * @return
     */
    public boolean insertBatch(Byte userId, List<Byte> roleIdList);

    /**
     * 根据id集合批量删除数据
     *
     * @param ids
     * @return
     */
    public boolean deleteByIdList(List<Byte> ids);

    /**
     * 根据id集合批量禁用
     *
     * @param ids
     * @return
     */
    public boolean disableByIdList(List<Byte> ids);

    /**
     * 更新用户数据
     */
    public boolean update(SysUser user, List<Byte> rids);

}
