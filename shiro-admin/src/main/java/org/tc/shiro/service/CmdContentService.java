package org.tc.shiro.service;

import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.CmdContent;

import java.util.List;

public interface CmdContentService extends IBaseService<CmdContent> {

    /**
     * 根据外键查询
     *
     * @param cmdId
     */
    public List<String> selectByCmdId(Integer cmdId);

}
