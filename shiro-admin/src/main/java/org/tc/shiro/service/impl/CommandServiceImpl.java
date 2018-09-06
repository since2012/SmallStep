package org.tc.shiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.core.consts.IRobotConst;
import org.tc.shiro.mapper.CommandContentMapper;
import org.tc.shiro.mapper.CommandMapper;
import org.tc.shiro.po.Command;
import org.tc.shiro.po.CommandContent;
import org.tc.shiro.service.CommandService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 查询相关的业务功能
 */
@Service
public class CommandServiceImpl extends BaseServiceImpl<CommandMapper, Command> implements CommandService {

    @Autowired
    private CommandContentMapper contentMapper;

    private void genContent(Integer id, List<String> contentList) {
        if (CollectionUtils.isNotEmpty(contentList)) {
            List<CommandContent> contents = new ArrayList<CommandContent>();
            for (String content : contentList) {
                CommandContent commandContent = new CommandContent();
                commandContent.setCommandId(id);
                commandContent.setContent(content);
                contents.add(commandContent);
            }
            contentMapper.insertList(contents);
        }
    }

    @Override
    @Transactional
    public void add(Command command, List<String> contentList) {
        baseMapper.insertUseGeneratedKeys(command);
        Integer id = command.getId();
        genContent(id, contentList);
    }

    @Override
    @Transactional
    public void edit(Command command, List<String> contentList) {
        Integer id = command.getId();
        contentMapper.deleteByFk(id);
        baseMapper.updateByPrimaryKey(command);
        genContent(id, contentList);
    }

    @Override
    @Transactional
    public void delCascade(Integer id) {
        contentMapper.deleteByFk(id);
        baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void deleteCascadeBatch(List<Integer> idList) {
        contentMapper.deleteByFkList(idList);
        baseMapper.deleteBatch(idList);
    }

    @Override
    public String reply(String name) {
        //查看帮助
        if (IRobotConst.HELP_COMMAND.equals(name)) {
            List<Command> list = baseMapper.selectAll();
            StringBuilder result = new StringBuilder();
            for (Command cmd : list) {
                result.append("回复[" + cmd.getName() + "]可以查看" + cmd.getDescription());
                result.append("<br/>");
            }
            return result.toString();
        }

        //其他情况
        Command command = baseMapper.getByName(name);
        if (command != null) {
            List<String> contents = contentMapper.selectByFk(command.getId());
            //随机获取1条
            int i = new Random().nextInt(contents.size());
            return contents.get(i);
        }
        //其他情况
        return IRobotConst.NO_MATCHING_CONTENT;
    }

    @Override
    public PageInfo<Command> page(Command command, Integer pageNo, Integer pageSize, String sort) {

        if (StringUtils.isBlank(command.getName())) {
            command.setName(null);
        }
        if (StringUtils.isBlank(command.getDescription())) {
            command.setDescription(null);
        }
        PageHelper.startPage(pageNo, pageSize, sort);

//        Example example = new Example(Command.class);
//        Example.Criteria criteria = example.createCriteria();
//        //where条件
//
//        if (!StringUtils.isBlank(command.getName())) {
//            criteria.andLike("name", "%" + command.getName().trim() + "%");
//        }
//        if (!StringUtils.isBlank(command.getDescription())) {
//            criteria.andLike("description", "%" + command.getDescription().trim() + "%");
//        }
//        List<Command> list = baseMapper.selectByExample(example);
        List<Command> list = baseMapper.list(command);
        if (CollectionUtils.isNotEmpty(list)) {
            for (Command cmd : list) {
                List<String> contents = contentMapper.selectByFk(cmd.getId());
                cmd.setContents(contents);
            }
        }
        //用PageInfo对结果进行包装
        PageInfo<Command> page = new PageInfo<Command>(list);
        return page;
    }

}
