package com.me92100984.aop.ex05;

import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;

//ProxyFactory를 bean으로 등록하기 위해서... 보통 모아서 작업?
@Configuration
public class Config {
    @Autowired
    private Seasoning seasoning;
    // @Autowired - 순환참조됨..;
    // private ProxyFactory factory;
    @Autowired
    @Qualifier("target")
    private First first;
    
    @Bean
    public ProxyFactory proxyfactory() {
        return new ProxyFactory();
    }

    @Bean
    public Pointcut pointcut() {
        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression("execution(* two(..))");
        return pc;
    }

    @Bean
    public Advisor advisor() {
        return new DefaultPointcutAdvisor(pointcut(), seasoning);
    }

    @Bean("proxy")
    public First first() {
        ProxyFactory factory = proxyfactory(); //순환참조되지않도록
        factory.setTarget(first);
        factory.addAdvisor(advisor());
        return (First)factory.getProxy();
    }
}
