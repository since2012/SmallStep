package org.tc.shiro.modular.system.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.mapper.NoticeMapper;
import org.tc.shiro.modular.system.service.INoticeService;
import org.tc.shiro.po.Notice;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * 通知Service实现
 *
 * @author TC
 * @Date 2018-07-29 16:40:15
 */
@Service
public class NoticeServiceImpl extends BaseServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Override
    public List<Notice> list(String title, String content) {
        Example example = new Example(Notice.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(title)) {
            criteria.andLike("title", "%" + title + "%");
        }
        if (StringUtils.isNotBlank(content)) {
            criteria.andLike("content", "%" + content + "%");
        }
        example.setOrderByClause("createtime desc");
        List<Notice> list = baseMapper.selectByExample(example);
        return list;
    }
}
