package org.tc.shiro.modular.system.service;

import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.Notice;

import java.util.List;

/**
 * 通知Service
 *
 * @author TC
 * @Date 2018-07-29 16:40:15
 */
public interface INoticeService extends IBaseService<Notice> {

    /**
     * 搜索
     *
     * @return
     */
    public List<Notice> list(String title,String content);
}
