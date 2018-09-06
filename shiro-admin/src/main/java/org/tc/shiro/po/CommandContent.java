package org.tc.shiro.po;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "command_content")
public class CommandContent {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "COMMAND_ID")
    private Integer commandId;

    @Override
    public String toString() {
        return content;
    }
}