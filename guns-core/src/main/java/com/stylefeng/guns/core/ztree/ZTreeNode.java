package com.stylefeng.guns.core.ztree;

import lombok.Data;

/**
 * jquery ztree 插件的节点
 *
 * @author fengshuonan
 * @date 2017年2月17日 下午8:25:14
 */
@Data
public class ZTreeNode {

    private Long id;    //节点id
    private String name;//节点名称
    private String code;
    private Boolean open;//是否打开节点
    private Boolean checked;//是否被选中
    private Long pid;//父节点id

    public static ZTreeNode createRoot() {
        ZTreeNode zTreeNode = new ZTreeNode();
        zTreeNode.setId(0L);
        zTreeNode.setName("顶级");
        zTreeNode.setCode("0");
        zTreeNode.setChecked(false);
        zTreeNode.setOpen(true);
        zTreeNode.setPid(0L);
        return zTreeNode;
    }
}
