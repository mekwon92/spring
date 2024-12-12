package com.me92100984.aop.ex02.adv;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

import io.micrometer.common.lang.Nullable;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Packaging implements AfterReturningAdvice { //  AfterReturn - 대상 메서드가 예외 없이 성공적으로 실행되었을 때만 실행

    @Override
    public void afterReturning(@Nullable Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        log.info("포장을 진행한다.");
    }
    
    
}
