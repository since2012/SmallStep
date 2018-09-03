package com.stylefeng.guns.core.datascope;

import lombok.Data;

import java.util.List;

/**
 * 数据范围
 *
 * @author fengshuonan
 * @date 2017-07-23 22:19
 */
@Data
public class DataScope {

    /**
     * 限制范围的字段名称
     */
    private String fieldName = "deptid";

    /**
     * 具体的数据范围
     */
    private List<Integer> ids;

    public DataScope() {
    }

    public DataScope(List<Integer> ids) {
        this.ids = ids;
    }

    public DataScope(String fieldName, List<Integer> ids) {
        this.fieldName = fieldName;
        this.ids = ids;
    }
}
