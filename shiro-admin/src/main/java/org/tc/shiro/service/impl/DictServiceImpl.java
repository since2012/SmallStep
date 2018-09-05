package org.tc.shiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tc.mybatis.exception.GunsException;
import org.tc.mybatis.service.impl.BaseServiceImpl;
import org.tc.shiro.core.common.constant.factory.MutiStrFactory;
import org.tc.shiro.core.common.exception.BizExceptionEnum;
import org.tc.shiro.mapper.DictMapper;
import org.tc.shiro.po.Dict;
import org.tc.shiro.service.IDictService;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DictServiceImpl extends BaseServiceImpl<DictMapper, Dict> implements IDictService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Dict addDict(String dictCode, String dictName, String dictTips, String dictValues) {
        return this.saveDict(dictCode, dictName, dictTips, dictValues);
    }

    private boolean existsByPidEqualsAndCodeEquals(Integer pid, String code) {
        return retBool(baseMapper.selectCountByPidAndCode(pid, code));
    }

    private Dict saveDict(String dictCode, String dictName, String dictTips, String dictValues) {
        boolean exists = existsByPidEqualsAndCodeEquals(0, dictCode);
        //判断有没有该字典
        if (exists) {
            throw new GunsException(BizExceptionEnum.DICT_EXISTED);
        }
        //解析dictValues
        List<Map<String, String>> items = MutiStrFactory.parseKeyValue(dictValues);

        //添加字典
        Dict dict = new Dict();
        dict.setName(dictName);
        dict.setCode(dictCode);
        dict.setTips(dictTips);
        dict.setNum(0);
        dict.setPid(0);
        this.baseMapper.insertUseGeneratedKeys(dict);

        //添加字典条目
        for (Map<String, String> item : items) {
            String code = item.get(MutiStrFactory.MUTI_STR_CODE);
            String name = item.get(MutiStrFactory.MUTI_STR_NAME);
            String num = item.get(MutiStrFactory.MUTI_STR_NUM);
            Dict itemDict = new Dict();
            itemDict.setPid(dict.getId());
            itemDict.setCode(code);
            itemDict.setName(name);

            try {
                itemDict.setNum(Integer.valueOf(num));
            } catch (NumberFormatException e) {
                throw new GunsException(BizExceptionEnum.DICT_MUST_BE_NUMBER);
            }
            this.baseMapper.insertUseGeneratedKeys(itemDict);
        }
        return dict;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Dict editDict(Integer dictId, String dictCode, String dictName, String dictTips, String dicts) {
        //删除之前的字典
        this.deleteDictCascade(dictId);
        //重新添加新的字典
        return this.saveDict(dictCode, dictName, dictTips, dicts);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDictCascade(Integer dictId) {
        //删除这个字典的子词典
        baseMapper.delByPidEq(dictId);
        //删除这个词典
        baseMapper.deleteByPrimaryKey(dictId);
    }

    @Override
    public List<Dict> selectByParentCode(String code) {
        return this.baseMapper.findChildrenByParentCode(code);
    }

    @Override
    public PageInfo<Dict> page(String name, String tips, Integer pageNo, Integer pageSize, String sort) {
        PageHelper.startPage(pageNo, pageSize, sort);
        List<Dict> list = baseMapper.list(name, tips);
        //用PageInfo对结果进行包装
        PageInfo<Dict> page = new PageInfo<Dict>(list);
        return page;
    }

    @Override
    public List<Dict> findSubDict(Integer pid) {
        return baseMapper.findByPidEquals(pid);
    }
}
