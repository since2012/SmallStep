package org.tc.shiro.mapper;

import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.SysRoleResource;

import java.util.List;

public interface SysRoleResourceMapper extends MyMapper<SysRoleResource> {

    public int insertBatch(@Param("roleId") Byte roleId, @Param("resIds") List<Byte> resIds);

    public int deleteByRid(@Param("id") Byte id);

    public int deleteByRids(@Param("ids") List<Byte> ids);

    public List<Byte> selectByRid(@Param("id") Byte id);
}
