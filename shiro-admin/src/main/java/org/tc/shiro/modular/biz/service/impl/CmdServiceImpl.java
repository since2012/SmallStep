package org.tc.shiro.modular.biz.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.core.common.constant.IRobotConst;
import org.tc.shiro.mapper.CmdContentMapper;
import org.tc.shiro.mapper.CmdMapper;
import org.tc.shiro.modular.biz.service.CmdService;
import org.tc.shiro.po.Cmd;
import org.tc.shiro.po.CmdContent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 查询相关的业务功能
 */
@Service
public class CmdServiceImpl extends BaseServiceImpl<CmdMapper, Cmd> implements CmdService {

    @Autowired
    private CmdContentMapper contentMapper;

    private void genContent(Integer id, List<String> contentList) {
        if (CollectionUtils.isNotEmpty(contentList)) {
            List<CmdContent> contents = new ArrayList<CmdContent>();
            for (String content : contentList) {
                CmdContent cmdContent = new CmdContent();
                cmdContent.setCmdid(id);
                cmdContent.setContent(content);
                contents.add(cmdContent);
            }
            contentMapper.insertList(contents);
        }
    }

    @Override
    @Transactional
    public void add(Cmd cmd, List<String> contentList) {
        baseMapper.insertUseGeneratedKeys(cmd);
        Integer id = cmd.getId();
        genContent(id, contentList);
    }

    @Override
    @Transactional
    public void edit(Cmd cmd, List<String> contentList) {
        Integer id = cmd.getId();
        contentMapper.deleteByCmdId(id);
        baseMapper.updateByPrimaryKey(cmd);
        genContent(id, contentList);
    }

    @Override
    @Transactional
    public void delCascade(Integer id) {
        contentMapper.deleteByCmdId(id);
        baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteCascadeBatch(List<Integer> idList) {
        contentMapper.delByCmdIdList(idList);
        baseMapper.deleteBatch(idList);
    }

    @Override
    public String reply(String name) {
        //查看帮助
        if (IRobotConst.HELP_COMMAND.equals(name)) {
            List<Cmd> list = baseMapper.selectAll();
            StringBuilder result = new StringBuilder();
            for (Cmd cmd : list) {
                result.append("回复[" + cmd.getName() + "]可以查看" + cmd.getDetail());
                result.append("<br/>");
            }
            return result.toString();
        }

        //其他情况
        Cmd cmd = baseMapper.getByName(name);
        if (cmd != null) {
            List<String> contents = contentMapper.selectByCmdId(cmd.getId());
            //随机获取1条
            int i = new Random().nextInt(contents.size());
            return contents.get(i);
        }
        //其他情况
        return IRobotConst.NO_MATCHING_CONTENT;
    }

    @Override
    public PageInfo<Cmd> page(Cmd cmd, Integer pageNo, Integer pageSize, String sort) {

        if (StringUtils.isBlank(cmd.getName())) {
            cmd.setName(null);
        }
        if (StringUtils.isBlank(cmd.getDetail())) {
            cmd.setDetail(null);
        }
        PageHelper.startPage(pageNo, pageSize, sort);

//        Example example = new Example(Cmd.class);
//        Example.Criteria criteria = example.createCriteria();
//        //where条件
//
//        if (!StringUtils.isBlank(cmd.getName())) {
//            criteria.andLike("name", "%" + cmd.getName().trim() + "%");
//        }
//        if (!StringUtils.isBlank(cmd.getDescription())) {
//            criteria.andLike("description", "%" + cmd.getDescription().trim() + "%");
//        }
//        List<Cmd> list = baseMapper.selectByExample(example);
        List<Cmd> list = baseMapper.list(cmd);
        //用PageInfo对结果进行包装
        PageInfo<Cmd> page = new PageInfo<Cmd>(list);
        return page;
    }

}
