package org.tc.shiro.mapper;

import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.Relation;

import java.util.List;

/**
 * <p>
 * 角色和菜单关联表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
public interface RelationMapper extends MyMapper<Relation> {

    /**
     * 删除某个角色的所有权限
     *
     * @param roleId 角色id
     * @return
     * @date 2017年2月13日 下午7:57:51
     */
    int deleteByRoleId(@Param("roleId") Integer roleId);

    /**
     * 删除menu关联的relation
     *
     * @param menuId
     * @return
     * @date 2017年2月19日 下午4:10:59
     */
    int deleteByMenuId(@Param("menuId") Long menuId);

    /**
     * 查询角色权限ID集合
     *
     * @param roleId
     * @return
     */
    List<Long> menuidListByRoleId(@Param("roleId") Integer roleId);

}