package com.xr.base;

import com.xr.base.service.SysDeptService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaseApplicationTests {
    @Autowired
    private SysDeptService sysDeptService;

    @Test
    void contextLoads() {

    }

}
