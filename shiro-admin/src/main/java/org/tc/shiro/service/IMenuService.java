package org.tc.shiro.service;

import com.stylefeng.guns.core.ztree.MenuNode;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.Menu;

import java.util.List;

/**
 * 菜单服务
 *
 * @author fengshuonan
 * @date 2017-05-05 22:19
 */
public interface IMenuService extends IBaseService<Menu> {

    /**
     * 删除菜单包含所有子菜单
     *
     * @author stylefeng
     * @Date 2017/6/13 22:02
     */
    void delMenuCascade(Long menuId);

    /**
     * 新增
     *
     * @param menu
     */
    public void add(Menu menu);
    public void update(Menu menu);

    /**
     * 切换状态(级联全启用全禁用)
     *
     * @author stylefeng
     * @Date 2017/6/13 22:02
     */
    void switchStatusCascade(Long menuId);

    /**
     * 根据条件查询LIST
     *
     * @param name
     * @param level
     * @return
     */
    public List<Menu> list(String name, String level);

    /**
     * 获取资源url通过角色id
     *
     * @param roleId
     * @return
     * @date 2017年2月19日 下午7:12:38
     */
    List<String> getUrlsByRoleId(Integer roleId);

    /**
     * 获取菜单列表树
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
    List<ZTreeNode> menuTree();

    /**
     * 获取菜单列表树
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
    List<ZTreeNode> getCheckedMenuTree(Integer roleId);


    /**
     * 根据角色获取菜单
     *
     * @param roleIds
     * @return
     * @date 2017年2月19日 下午10:35:40
     */
    List<MenuNode> getListByRoleIds(List<Integer> roleIds);

    /**
     * 通过code获取数据
     *
     * @param code
     * @return
     */
    public Menu getByCode(String code);
}
