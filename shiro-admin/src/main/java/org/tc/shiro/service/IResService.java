package org.tc.shiro.service;

import org.tc.mybatis.service.IBaseService;
import org.tc.shiro.po.SysResource;

import java.util.List;

/**
 * Created by Liwei on 2016/9/19.
 */
public interface IResService extends IBaseService<SysResource> {

    /**
     * 通过主键批量删除
     *
     * @param ids
     * @return
     */
    public boolean deleteBatchByIds(List<Byte> ids);

    /**
     * 通过主键批量假删
     *
     * @param ids
     * @return
     */
    public boolean diableBatchByIds(List<Byte> ids);

    /**
     * 查询用户所拥有的权限
     *
     * @param uid
     * @return
     */
    List<SysResource> selectListByUid(Byte uid);

}
