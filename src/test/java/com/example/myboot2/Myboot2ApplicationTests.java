package com.example.myboot2;

import com.example.myboot2.service.SomeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class Myboot2ApplicationTests {

    @Test
    void contextLoads() {

    }

    @Autowired
    @Qualifier("someServiceImpl")
    private SomeService someService;

    @Autowired
    @Qualifier("otherServiceImpl")
    private SomeService otherService;

    @Test
    public void test01() {
        someService.doSome();
        otherService.doSome();
    }
}
