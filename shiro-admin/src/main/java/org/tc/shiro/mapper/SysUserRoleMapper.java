package org.tc.shiro.mapper;

import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper extends MyMapper<SysUserRole> {

    /**
     * 生成用户-角色映射
     *
     * @param userId
     * @param roleIds
     * @return
     */
    public int insertBatch(@Param("userId") Byte userId, @Param("roleIds") List<Byte> roleIds);

    /**
     * 根据用户或者角色id批量删除
     *
     * @param id
     * @return
     */
    public int deleteByUid(@Param("id") Byte id);
    public int deleteByRid(@Param("id") Byte id);
    public int deleteByUids(@Param("ids") List<Byte> ids);
    public int deleteByRids(@Param("ids") List<Byte> ids);

}