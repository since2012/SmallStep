package org.tc.shiro.warpper;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.tc.mybatis.warpper.BeanWarpper;
import org.tc.shiro.core.common.constant.factory.ConstantFactory;
import org.tc.shiro.po.Notice;
import org.tc.shiro.vo.NoticeVo;

/**
 * 通知的包装
 */
public class NoticeWrapper extends BeanWarpper<Notice, NoticeVo> {

    @Override
    public NoticeVo warpBean(Notice po) {
        NoticeVo vo = new NoticeVo();
        BeanUtils.copyProperties(po, vo);
        Integer creater = po.getCreater();
        //非转义化
        vo.setContent(StringEscapeUtils.unescapeHtml4(po.getContent()));
        vo.setCreaterName(ConstantFactory.me().getUserNameById(creater));
        return vo;
    }

}
