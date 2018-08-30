package org.tc.shiro.service;

import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.SysRole;
import org.tc.shiro.po.SysRoleResource;

import java.util.List;

/**
 * Created by Liwei on 2016/9/18.
 */
public interface IRoleService extends IBaseService<SysRole> {

    /**
     * 通过角色IDs批量删除
     *
     * @param ids
     * @return
     */
    public boolean deleteBatchByIds(List<Byte> ids);

    /**
     * 通过角色IDs批量禁用
     *
     * @param ids
     * @return
     */
    public boolean disableBatchByIds(List<Byte> ids);

    /**
     * 根据角色 id 和权限 id 删除一条用户权限关联数据
     */
    boolean delete(SysRoleResource sysRoleResource);

    /**
     * 更新角色权限
     *
     * @param roleId
     * @param resIds
     * @return
     */
    public boolean updateResource(Byte roleId, List<Byte> resIds);

    /**
     * 查询用户的角色
     * @param uid
     * @return
     */
    public List<Byte> selectRoleByUid(Byte uid);

    /**
     * 查询当前角色拥有的资源
     *
     * @param rid
     * @return
     */
    public List<Byte> selectResIdsByRid(Byte rid);

}
