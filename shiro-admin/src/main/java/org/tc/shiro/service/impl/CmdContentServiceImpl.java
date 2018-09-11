package org.tc.shiro.service.impl;

import org.springframework.stereotype.Service;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.mapper.CmdContentMapper;
import org.tc.shiro.po.CmdContent;
import org.tc.shiro.service.CmdContentService;

import java.util.List;

/**
 * 查询相关的业务功能
 */
@Service
public class CmdContentServiceImpl extends BaseServiceImpl<CmdContentMapper, CmdContent> implements CmdContentService {

    @Override
    public List<String> selectByCmdId(Integer cmdId) {
        return baseMapper.selectByCmdId(cmdId);
    }
}
