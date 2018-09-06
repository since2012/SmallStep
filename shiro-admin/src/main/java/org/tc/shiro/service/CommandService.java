package org.tc.shiro.service;

import com.github.pagehelper.PageInfo;
import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.Command;

import java.util.List;

public interface CommandService extends IBaseService<Command> {

    /**
     * 新增
     *
     * @param command
     */
    public void add(Command command, List<String> contentList);

    /**
     * 编辑
     *
     * @param command
     */
    public void edit(Command command, List<String> contentList);

    /**
     * 级联删除
     *
     * @param id
     */
    public void delCascade(Integer id);

    /**
     * 批量删除
     */
    void deleteCascadeBatch(List<Integer> idList);

    /**
     * 分页查询
     *
     * @param command
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Command> page(Command command, Integer pageNo, Integer pageSize, String sort);

    /**
     * 回复查询
     *
     * @param name
     * @return
     */
    String reply(String name);
}
