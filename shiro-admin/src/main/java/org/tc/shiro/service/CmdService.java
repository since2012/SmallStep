package org.tc.shiro.service;

import com.github.pagehelper.PageInfo;
import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.Cmd;

import java.util.List;

public interface CmdService extends IBaseService<Cmd> {

    /**
     * 新增
     *
     * @param cmd
     */
    public void add(Cmd cmd, List<String> contentList);

    /**
     * 编辑
     *
     * @param cmd
     */
    public void edit(Cmd cmd, List<String> contentList);

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
     * @param cmd
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Cmd> page(Cmd cmd, Integer pageNo, Integer pageSize, String sort);

    /**
     * 回复查询
     *
     * @param name
     * @return
     */
    String reply(String name);
}
