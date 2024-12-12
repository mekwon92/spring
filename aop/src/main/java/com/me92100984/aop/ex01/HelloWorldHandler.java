package com.me92100984.aop.ex01;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class HelloWorldHandler<T> implements InvocationHandler {
    private T t;
    
    public HelloWorldHandler(T t) {
        this.t = t;
    }

    //around advice
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable { // invoke는 InvocationHandler의 추상메서드
        long start = System.currentTimeMillis();
        Object o = method.invoke(t, args); //여기서의 invoke는 끼어드는 역할
        log.info(System.currentTimeMillis() - start + "ms");

        return o;
    }
    
}
