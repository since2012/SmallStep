package org.tc.shiro.warpper;

import com.stylefeng.guns.core.util.ToolUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.tc.mybatis.warpper.BeanWarpper;
import org.tc.shiro.core.common.constant.factory.ConstantFactory;
import org.tc.shiro.po.Dict;
import org.tc.shiro.vo.DictVo;

import java.util.List;

/**
 * 字典列表的包装
 *
 * @author fengshuonan
 * @date 2017年4月25日 18:10:31
 */
public class DictWarpper extends BeanWarpper<Dict, DictVo> {


    @Override
    public DictVo warpBean(Dict po) {
        DictVo vo = new DictVo();
        BeanUtils.copyProperties(po, vo);
        StringBuffer detail = new StringBuffer();
        Integer id = po.getId();
        List<Dict> dicts = ConstantFactory.me().findChildrenInDict(id);
        if (CollectionUtils.isNotEmpty(dicts)) {
            for (Dict dict : dicts) {
                detail.append(dict.getCode() + ":" + dict.getName() + ",");
            }
            vo.setDetail(ToolUtil.removeSuffix(detail.toString(), ","));
        }
        return vo;
    }

}
