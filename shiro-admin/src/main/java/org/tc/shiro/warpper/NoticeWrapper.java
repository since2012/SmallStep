package org.tc.shiro.warpper;

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.beans.BeanUtils;
import org.tc.mybatis.warpper.BeanWarpper;
import org.tc.shiro.core.common.constant.factory.ConstantFactory;
import org.tc.shiro.po.Notice;
import org.tc.shiro.vo.NoticeVo;

/**
 * 部门列表的包装
 *
 * @author fengshuonan
 * @date 2017年4月25日 18:10:31
 */
public class NoticeWrapper extends BeanWarpper<Notice, NoticeVo> {

    @Override
    public NoticeVo warpBean(Notice po) {
        NoticeVo vo = new NoticeVo();
        BeanUtils.copyProperties(po, vo);
        Integer creater = po.getCreater();
        vo.setContent(StringEscapeUtils.unescapeHtml4(po.getContent()));
        vo.setCreaterName(ConstantFactory.me().getUserNameById(creater));
        return vo;
    }

}
