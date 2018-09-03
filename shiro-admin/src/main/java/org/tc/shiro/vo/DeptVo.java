package org.tc.shiro.vo;

import lombok.Data;

@Data
public class DeptVo {
    private int id;
    private Integer num;
    private Integer pid;
    private String pids;
    private String simplename;
    private String fullname;
    private String tips;
    private Integer version;

    private String pName;
}
