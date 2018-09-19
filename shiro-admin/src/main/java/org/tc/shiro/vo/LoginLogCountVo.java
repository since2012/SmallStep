package org.tc.shiro.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LoginLogCountVo {
    private List<String> dateList = new ArrayList<>();
    private List<Long> numList = new ArrayList<>();
}
