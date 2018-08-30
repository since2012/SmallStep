package org.tc.shiro.mapper;

import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.SysUser;

import java.util.List;

public interface SysUserMapper extends MyMapper<SysUser> {

    /**
     * 查询具有该角色的用户
     *
     * @param rid
     * @return
     */
    public List<SysUser> selectByRid(@Param("rid") Byte rid);

    /**
     * 根据ID集合删除
     *
     * @param ids
     * @return
     */
    public int deleteByIdList(@Param("ids") List<Byte> ids);

    /**
     * 根据ID集合禁用
     *
     * @param ids
     * @return
     */
    public int disableByIdList(@Param("ids") List<Byte> ids);

}