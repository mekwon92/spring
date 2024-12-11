package com.me92100984.di.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class PostServiceTests {
    
    @Autowired //(required = false) //반드시 의존하지는 않는다(true가 디폴트 ->  this.service가 null일경우 nullpointexception 뜸)
    @Qualifier("postService") // gallery와 normal 중 특정하기 위해서
    private PostService service;

    @Test
    public void testExist() {
        assertNotNull(service);
    }

    @Test
    public void testList() {
        System.out.println("??");
        service.list().forEach(log::info);
    }
    
}
