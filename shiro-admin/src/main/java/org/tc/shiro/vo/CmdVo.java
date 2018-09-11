package org.tc.shiro.vo;

import lombok.Data;

import java.util.List;

@Data
public class CmdVo {
    private Integer id;
    private String name;
    private String detail;
    private List<String> contents;
}