package org.tc.shiro.mapper;

import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.Dict;

import java.util.List;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author stylefeng
 * @since 2017-07-11
 */
public interface DictMapper extends MyMapper<Dict> {

    /**
     * 根据编码获取词典列表
     */
    List<Dict> selectByCode(@Param("code") String code);

    /**
     * 查询字典列表
     */
    List<Dict> list(@Param("name") String name, @Param("tips") String tips);

    /**
     * 删除所有子项
     *
     * @param pid
     */
    public void delByPidEq(@Param("pid") Integer pid);

    /**
     * 是否已经存在
     *
     * @param pid
     * @param code
     * @return
     */
    public int selectCountByPidAndCode(@Param("pid") Integer pid, @Param("code") String code);

    /**
     * 查找所有子项目
     *
     * @param pid
     * @return
     */
    public List<Dict> findByPidEquals(@Param("pid") Integer pid);

    /**
     * 根绝code查询子项
     *
     * @param code
     * @return
     */
    public List<Dict> findChildrenByParentCode(@Param("code") String code);

    /**
     * 通过name查找
     *
     * @param name
     * @return
     */
    public Dict findByNameEquals(@Param("name") String name);
}