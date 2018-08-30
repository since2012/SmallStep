package org.tc.shiro.mapper;

import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.SysResource;

import java.util.List;

public interface SysResourceMapper extends MyMapper<SysResource> {

    /**
     * 通过主键批量删除
     *
     * @param ids
     * @return
     */
    public int deleteByIdList(@Param("ids") List<Byte> ids);

    /**
     * 通过主键批量更新
     *
     * @param ids
     * @return
     */
    public int diableByIdList(@Param("ids") List<Byte> ids);

    /**
     * 查询用户的资源访问权限
     *
     * @param uid
     * @return
     */
    public List<SysResource> selectByUid(@Param("uid") int uid);

    /**
     * 查询角色的资源访问权限
     *
     * @param rid
     * @return
     */
    public List<SysResource> selectByRid(@Param("rid") int rid);
}