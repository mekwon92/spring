package com.me92100984.aop.ex02.adv;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Sourcing implements MethodInterceptor { //  Around - 전체 과정을 감싸고 제어(대상 메서드 호출 전, 중간, 후에 원하는 로직을 추가 가능)
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String source = invocation.getArguments()[0].toString();
        log.info(source + " 양념을 만든다.");
        Object o = invocation.proceed();//실제할일 처리

        log.info(source + " 양념을 바른다.");
        return o;
    }
    
    
}
