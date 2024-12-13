package com.me92100984.aop.ex03;

import java.lang.reflect.Proxy;

import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

import com.me92100984.aop.ex02.adv.Packaging;
import com.me92100984.aop.ex03.adv.ThrowLog;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MartClient {
    public static void main(String[] args) {
        //1. ProxyFactory 생성
        ProxyFactory factory = new ProxyFactory();
        //2. target을 MartImpl로 지정
        factory.setTarget(new MartImpl());
        //3.ex02의 packaging을 advice로 지정
        factory.addAdvice(new Packaging());
        //4. ex03의 ThrowLog를 advice로 지정
        factory.addAdvice(new ThrowLog());
        //5. proxy객체 생성 후 getProduct 호출
        
        try{
            ((Mart)factory.getProxy()).getProduct("간장");
        }
        catch (RuntimeException r) {
            log.error(r.getMessage());
        }
    }
}
