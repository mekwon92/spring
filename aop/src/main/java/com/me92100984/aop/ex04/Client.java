package com.me92100984.aop.ex04;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import com.me92100984.aop.ex02.adv.Seasoning;
import com.me92100984.aop.ex03.adv.SimpleAdvs;

import lombok.extern.log4j.Log4j2;

//advice에 넣으면 모든 곳에 적용
//advisor에 넣으면 pointcut을 통해 조건문사용가능
@Log4j2
public class Client {
    public static void main(String[] args) {
        ProxyFactory factory = new ProxyFactory(new First());
        factory.addAdvice(new Seasoning());
        // ((First)factory.getProxy()).one();
        // ((First)factory.getProxy()).two();
        log.info("========================");
        factory = new ProxyFactory(new First());
        factory.addAdvisor(new DefaultPointcutAdvisor(new SimpleAdvs(), new Seasoning())); //one에만 적용
        // ((First)factory.getProxy()).one();
        // ((First)factory.getProxy()).two();
    }
    
}
