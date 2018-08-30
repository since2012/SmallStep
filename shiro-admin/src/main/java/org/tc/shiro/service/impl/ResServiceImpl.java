package org.tc.shiro.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.mapper.SysResourceMapper;
import org.tc.shiro.po.SysResource;
import org.tc.shiro.service.IResService;

import java.util.List;

/**
 * Created by Liwei on 2016/9/19.
 */
@Service
public class ResServiceImpl extends BaseServiceImpl<SysResourceMapper, SysResource> implements IResService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteBatchByIds(List<Byte> ids) {
        int count = baseMapper.deleteByIdList(ids);
        return retBool(count);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean diableBatchByIds(List<Byte> ids) {
        int count = baseMapper.diableByIdList(ids);
        return retBool(count);
    }

    @Override
    public List<SysResource> selectListByUid(Byte uid) {
        return baseMapper.selectByUid(uid);
    }
}
