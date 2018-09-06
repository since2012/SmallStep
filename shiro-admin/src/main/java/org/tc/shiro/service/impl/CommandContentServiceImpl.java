package org.tc.shiro.service.impl;

import org.springframework.stereotype.Service;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.mapper.CommandContentMapper;
import org.tc.shiro.po.CommandContent;
import org.tc.shiro.service.CommandContentService;

import java.util.List;

/**
 * 查询相关的业务功能
 */
@Service
public class CommandContentServiceImpl extends BaseServiceImpl<CommandContentMapper, CommandContent> implements CommandContentService {

    @Override
    public List<String> selectByCommandId(Integer commandId) {
        return baseMapper.selectByFk(commandId);
    }
}
