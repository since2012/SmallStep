package org.tc.shiro.service;

import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.CommandContent;

import java.util.List;

public interface CommandContentService extends IBaseService<CommandContent> {

    /**
     * 根据外键查询
     *
     * @param commandId
     */
    public List<String> selectByCommandId(Integer commandId);

}
