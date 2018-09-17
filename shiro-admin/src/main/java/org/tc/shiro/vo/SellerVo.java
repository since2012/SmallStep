package org.tc.shiro.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SellerVo {
    private Integer id;
    private String name;
    private String addr;
    private Integer status;
    private Date createtime;

    private String statusName;
}
