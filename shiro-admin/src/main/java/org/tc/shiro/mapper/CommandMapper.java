package org.tc.shiro.mapper;

import org.springframework.data.repository.query.Param;
import org.tc.mybatis.dao.MyMapper;
import org.tc.shiro.po.Command;

import java.util.List;

public interface CommandMapper extends MyMapper<Command> {

    /**
     * 查询符合要求记录
     *
     * @param command
     * @return
     */
    public List<Command> list(Command command);

    /**
     * 查询符合要求记录
     *
     * @param name
     * @return
     */
    public Command getByName(@Param("name") String name);

    /**
     * 批量删除
     *
     * @param idList
     * @return
     */
    public int deleteBatch(List<Integer> idList);
}