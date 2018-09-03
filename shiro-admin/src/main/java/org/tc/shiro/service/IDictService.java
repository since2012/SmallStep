package org.tc.shiro.service;

import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.Dict;

import java.util.List;

/**
 * 字典服务
 *
 * @author fengshuonan
 * @date 2017-04-27 17:00
 */
public interface IDictService extends IBaseService<Dict> {

    /**
     * 新增字典项
     *
     * @param dictCode   编码
     * @param dictName   名称
     * @param dictTips   提示
     * @param dictValues 下属值
     */
    Dict addDict(String dictCode, String dictName, String dictTips, String dictValues);

    /**
     * 编辑字典
     */
    Dict editDict(Integer dictId, String dictCode, String dictName, String dictTips, String dicts);

    /**
     * 删除字典
     */
    void deleteDictCascade(Integer dictId);

    /**
     * 根据父类编码获取词典列表
     */
    List<Dict> selectByParentCode(String code);

    /**
     * 查询字典列表
     */
    List<Dict> list(String name, String tips);

    /**
     * @param pid
     * @return
     */
    public List<Dict> findSubDict(Integer pid);
}
