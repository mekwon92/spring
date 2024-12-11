package com.me92100984.di.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.me92100984.di.service.PostService;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class PostTest {
    //이거안쓰고 log찍음?
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(PostTest.class);
        ApplicationContext ctx = application.run(args);
        
        PostService service = ctx.getBean(PostService.class);
        service.list().forEach(log::info);
    }
    
}
