package org.tc.shiro.po;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@Table(name = "command")
public class Command {
    @Id
    @Column(name = "ID")
    private Integer id;

    @NotEmpty
    @Column(name = "NAME")
    private String name;

    @NotEmpty
    @Column(name = "DESCRIPTION")
    private String description;

    @Transient
    private List<String> contents;
}