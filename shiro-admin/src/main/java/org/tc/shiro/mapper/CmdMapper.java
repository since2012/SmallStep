package org.tc.shiro.mapper;

import org.springframework.data.repository.query.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.Cmd;

import java.util.List;

public interface CmdMapper extends MyMapper<Cmd> {

    /**
     * 查询符合要求记录
     *
     * @param cmd
     * @return
     */
    public List<Cmd> list(Cmd cmd);

    /**
     * 查询符合要求记录
     *
     * @param name
     * @return
     */
    public Cmd getByName(@Param("name") String name);

    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    public int deleteBatch(List<Integer> idList);
}