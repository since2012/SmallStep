package org.tc.shiro.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "cmd_content")
public class CmdContent {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CMDID")
    private Integer cmdid;

    @Override
    public String toString() {
        return content;
    }
}