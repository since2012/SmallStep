package org.tc.shiro.mapper;

import com.stylefeng.guns.core.ztree.MenuNode;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.Menu;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
public interface MenuMapper extends MyMapper<Menu> {

    /**
     * 根据条件查询菜单
     *
     * @return
     * @date 2017年2月12日 下午9:14:34
     */
    List<Map<String, Object>> selectMenus(@Param("condition") String condition, @Param("level") String level);

    /**
     * 获取菜单列表树
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
    List<ZTreeNode> tree();

    /**
     * 获取菜单列表树
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
    List<ZTreeNode> treeByMenuIds(List<Long> menuIds);

    /**
     * 获取所有可访问的API
     *
     * @param roleId
     * @return
     */
    List<String> getAPIByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色获取菜单(侧边栏)
     *
     * @return
     * @date 2017年2月19日 下午10:35:40
     */
    List<MenuNode> getListByRoleIds(List<Integer> roleIds, @Param("notadmin") boolean notadmin);


    public int selectCountByCode(@Param("code") String code);

    /**
     * 通过code查询菜单
     *
     * @param code
     * @return
     */
    public Menu getByCode(@Param("code") String code);

    /**
     * 查询所有子菜单(包括禁用的)
     *
     * @param pcodes
     * @return
     */
    public List<Menu> getByPcodesLike(@Param("pcodes") String pcodes);

    /**
     * 批量查询
     *
     * @param menuids 如"1,2,3,4"
     * @return
     */
    public List<String> nameList(@Param("menuids") String menuids);

}