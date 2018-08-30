package org.tc.shiro.mapper;

import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.SysRole;

import java.util.List;

public interface SysRoleMapper extends MyMapper<SysRole> {

    /**
     * 查询用户的角色
     *
     * @param uid
     * @return
     */
    public List<SysRole> selectByUid(@Param("uid") Byte uid);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public int deleteByIdList(@Param("ids") List<Byte> ids);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public int diableByIdList(@Param("ids") List<Byte> ids);

}