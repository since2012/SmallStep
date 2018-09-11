package org.tc.shiro.modular.system.service;

import com.stylefeng.guns.core.ztree.ZTreeNode;
import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.Dept;

import java.util.List;

/**
 * 部门服务
 *
 * @author fengshuonan
 * @date 2017-04-27 17:00
 */
public interface IDeptService extends IBaseService<Dept> {

    /**
     * 删除部门
     */
    void deleteCascade(Integer deptId);

    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();

    /**
     * 获取所有部门列表
     */
    List<Dept> list(String name, String tips);
}
