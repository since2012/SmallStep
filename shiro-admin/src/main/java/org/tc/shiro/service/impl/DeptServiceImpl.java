package org.tc.shiro.service.impl;

import com.stylefeng.guns.core.ztree.ZTreeNode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tc.mybatis.exception.GunsException;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.core.common.exception.BizExceptionEnum;
import org.tc.shiro.mapper.DeptMapper;
import org.tc.shiro.po.Dept;
import org.tc.shiro.service.IDeptService;

import java.util.List;

@Service
@Transactional
public class DeptServiceImpl extends BaseServiceImpl<DeptMapper, Dept> implements IDeptService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCascade(Integer deptId) {
        boolean exists = baseMapper.existsWithPrimaryKey(deptId);
        if (!exists) {
            throw new GunsException(BizExceptionEnum.NO_THIS_RECORD);
        }
        baseMapper.delByPidsLike("%[" + deptId + "]%");
        baseMapper.deleteByPrimaryKey(deptId);
    }

    @Override
    public List<ZTreeNode> tree() {
        List<ZTreeNode> nodes = baseMapper.tree();
        return nodes;
    }

    @Override
    public List<Dept> list(String name, String tips) {
        return baseMapper.list(name, tips);
    }
}
