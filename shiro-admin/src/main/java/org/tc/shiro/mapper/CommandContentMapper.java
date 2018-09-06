package org.tc.shiro.mapper;

import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.CommandContent;

import java.util.List;

public interface CommandContentMapper extends MyMapper<CommandContent> {

    /**
     * 根据外键删除
     *
     * @param fk
     */
    public void deleteByFk(@Param("fk") Integer fk);

    /**
     * 根据外键LIST删除
     */
    public void deleteByFkList(List<Integer> fkList);

    /**
     * 根据外键查询
     *
     * @param fk
     */
    public List<String> selectByFk(@Param("fk") Integer fk);

}