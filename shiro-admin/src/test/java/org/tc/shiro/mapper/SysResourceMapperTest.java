package org.tc.shiro.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tc.shiro.po.SysResource;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SysResourceMapperTest {

    @Autowired
    private SysResourceMapper resourceMapper;

    @Test
    public void selectAllByUid() {
        List<SysResource> list = resourceMapper.selectByUid(1);
        log.info("{}", list);
    }
}