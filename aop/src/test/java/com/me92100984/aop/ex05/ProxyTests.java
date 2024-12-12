package com.me92100984.aop.ex05;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

//원래는 main말고 여기서 해야함. bean을 잘 잡음?
@SpringBootTest
public class ProxyTests {
    @Autowired
    @Qualifier("proxy")
    private First first;

    @Test
    public void testProxy() {
        first.one();
        first.two();
        first.two2();
    }
    
}
