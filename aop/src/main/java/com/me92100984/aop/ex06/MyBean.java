package com.me92100984.aop.ex06;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MyBean {
    private MyDependency myDependency;

    public void run() {
        myDependency.hello(8000);
        myDependency.hello(4000);
        myDependency.bye();
    }
    
}
