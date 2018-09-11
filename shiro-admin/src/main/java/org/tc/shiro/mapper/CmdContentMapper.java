package org.tc.shiro.mapper;

import org.apache.ibatis.annotations.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.CmdContent;

import java.util.List;

public interface CmdContentMapper extends MyMapper<CmdContent> {

    /**
     * 根据外键删除
     *
     * @param cmdId
     */
    public void deleteByCmdId(@Param("cmdId") Integer cmdId);

    /**
     * 根据外键LIST删除
     */
    public void delByCmdIdList(List<Integer> cmdIdList);

    /**
     * 根据外键查询
     *
     * @param cmdId
     */
    public List<String> selectByCmdId(@Param("cmdId") Integer cmdId);

}