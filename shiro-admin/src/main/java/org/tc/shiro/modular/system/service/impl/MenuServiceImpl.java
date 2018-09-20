package org.tc.shiro.modular.system.service.impl;

import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.core.ztree.MenuNode;
import com.stylefeng.guns.core.ztree.ZTreeNode;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tc.mybatis.exception.GunsException;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.core.common.constant.enums.DoubleStatus;
import org.tc.shiro.core.common.exception.BizExceptionEnum;
import org.tc.shiro.mapper.MenuMapper;
import org.tc.shiro.mapper.RelationMapper;
import org.tc.shiro.modular.system.service.IMenuService;
import org.tc.shiro.po.Menu;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 菜单服务
 *
 * @author fengshuonan
 * @date 2017-05-05 22:20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl extends BaseServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private RelationMapper relationMapper;

    /**
     * 删除菜单及角色配置
     *
     * @param menuId
     */
    private void delMenuById(Long menuId) {
        //删除关联的relation
        this.relationMapper.deleteByMenuId(menuId);
        //删除菜单
        this.baseMapper.deleteByPrimaryKey(menuId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMenuCascade(Long menuId) {
        Menu menu = baseMapper.selectByPrimaryKey(menuId);
        //删除所有子菜单
        List<Menu> menus = baseMapper.getByPcodesLike(menu.getPcodes() + "[" + menu.getCode() + "]");
        if (CollectionUtils.isNotEmpty(menus)) {
            for (Menu temp : menus) {
                delMenuById(temp.getId());
            }
        }
        //删除当前菜单
        delMenuById(menuId);
    }

    /**
     * 是否已经存在该CODE
     *
     * @param code
     * @return
     */
    private boolean existsByCode(String code) {
        return retBool(baseMapper.selectCountByCode(code));
    }

    @Override
    public void add(Menu menu) {
        //判断是否存在该编号
        boolean exists = existsByCode(menu.getCode());
        if (exists) {
            throw new GunsException(BizExceptionEnum.EXISTED_THE_MENU);
        }
        //设置父级菜单编号
        setPcode(menu);
        menu.setStatus(DoubleStatus.ENABLE.getCode());
        this.baseMapper.insertUseGeneratedKeys(menu);
    }

    @Override
    @Transactional
    public void update(Menu menu) {
        Menu oldMenu = baseMapper.selectByPrimaryKey(menu.getId());
        //设置父级菜单编号
        setPcode(menu);
        baseMapper.updateByPrimaryKeySelective(menu);
        String oldcode = oldMenu.getCode();
        String newcode = menu.getCode();
        if (!oldcode.equals(newcode)) {
            //所有子菜单
            List<Menu> menus = baseMapper.getByPcodesLike(oldcode);
            if (CollectionUtils.isNotEmpty(menus)) {
                for (Menu temp : menus) {
                    if (oldcode.equals(temp.getPcode())) {
                        temp.setPcode(newcode);
                    }
                    temp.setPcodes(temp.getPcodes().replace("[" + oldcode + "]",
                            "[" + newcode + "]"));
                    baseMapper.updateByPrimaryKey(temp);
                }
            }
        }
    }

    private void setPcode(Menu menu) {
        String pcode = menu.getPcode();
        if (StringUtils.isBlank(pcode) || pcode.equals("0")) {
            menu.setPcode("0");
            menu.setPcodes("[0],");
            menu.setLevels(1);
        } else {
            Menu pmenu = baseMapper.getByCode(pcode);
            //如果编号和父编号一致会导致无限递归
            String code = menu.getCode();
            if (code.equals(pcode)) {
                throw new GunsException(BizExceptionEnum.MENU_PCODE_COINCIDENCE);
            }
            menu.setLevels(pmenu.getLevels() + 1);
            menu.setPcodes(pmenu.getPcodes() + "[" + pcode + "],");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void switchStatusCascade(Long menuId) {
        Menu menu = baseMapper.selectByPrimaryKey(menuId);
        if (menu == null) {
            throw new GunsException(BizExceptionEnum.NO_THIS_MENU);
        }
        int code = 0;
        if (menu.getStatus().equals(DoubleStatus.ENABLE.getCode())) {
            code = DoubleStatus.DISABLE.getCode();
        } else {
            code = DoubleStatus.ENABLE.getCode();
        }

        //所有子菜单
        List<Menu> menus = baseMapper.getByPcodesLike(menu.getCode());
        if (CollectionUtils.isNotEmpty(menus)) {
            for (Menu temp : menus) {
                switchStatus(temp, code);
            }
        }
        switchStatus(menu, code);
    }

    private void switchStatus(Menu menu, Integer code) {
        menu.setStatus(code);
        baseMapper.updateByPrimaryKey(menu);
    }

    @Override
    public List<Menu> list(String name, Integer level) {
        Example example = new Example(Menu.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            criteria.andLike("name", "%" + name.trim() + "%");
        }
        if (level != null) {
            criteria.andEqualTo("levels", level);
        }
        example.setOrderByClause("levels asc,num asc");
        List<Menu> list = baseMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<String> getUrlsByRoleId(Integer roleId) {
        return this.baseMapper.getAPIByRoleId(roleId);
    }

    @Override
    public List<ZTreeNode> menuTree() {
        List<ZTreeNode> list = this.baseMapper.tree();
        return list;
    }

    @Override
    public List<ZTreeNode> getCheckedMenuTree(Integer roleId) {
        List<Long> menuIds = this.relationMapper.menuidListByRoleId(roleId);
        List<ZTreeNode> list = null;
        if (ToolUtil.isEmpty(menuIds)) {
            list = this.baseMapper.tree();
        } else {
            list = this.baseMapper.treeByMenuIds(menuIds);
        }
        return list;
    }

    @Override
    public List<MenuNode> getListByRoleIds(List<Integer> roleIds) {
        List<MenuNode> list = this.baseMapper.getListByRoleIds(roleIds, true);
        return list;
    }

    @Override
    public Menu getByCode(String code) {
        return baseMapper.getByCode(code);
    }
}
