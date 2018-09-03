package org.tc.shiro.mapper;

import com.stylefeng.guns.core.ztree.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.Role;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
public interface RoleMapper extends MyMapper<Role> {

    /**
     * 根据条件查询角色列表
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Map<String, Object>> selectRoles(@Param("condition") String condition);

    /**
     * 查询所有
     *
     * @return
     */
    List<ZTreeNode> roleTree();

    /**
     * 已选中树
     *
     * @return
     */
    List<ZTreeNode> checkedRoleTree(List<String> roleids);


    /**
     * 通过IDS获取角色名list
     *
     * @param roleids
     * @return
     */
    public List<String> nameList(List<String> roleids);

}