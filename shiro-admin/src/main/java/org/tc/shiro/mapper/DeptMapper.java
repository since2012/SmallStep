package org.tc.shiro.mapper;

import com.stylefeng.guns.core.ztree.ZTreeNode;
import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.Dept;

import java.util.List;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
public interface DeptMapper extends MyMapper<Dept> {

    /**
     * 删除所有子部门
     *
     * @param pids
     */
    public void delByPidsLike(String pids);

    /**
     * 查询
     *
     * @return
     */
    List<Dept> list(@Param("name") String name, @Param("tips") String tips);

    /**
     * 树形展示
     *
     * @return
     */
    public List<ZTreeNode> tree();

    /**
     * 查询子部门
     *
     * @param pids
     * @return
     */
    public List<Integer> subDeptIdList(String pids);

    /**
     * 部门范围
     *
     * @param pids
     * @return
     */
    public List<Integer> deptAndSubDept(Integer id, String pids);

}