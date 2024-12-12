package com.me92100984.aop.ex04;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import com.me92100984.aop.ex02.adv.Seasoning;

public class AspectJClient {
    public static void main(String[] args) {
        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        // pc.setExpression("execution(* two(..))");
        // pc.setExpression("execution(int two(..))"); // 아예 안걸림 int 반환이 없응께?
        pc.setExpression("execution(* two*(..))"); //two, two2 모두 걸리게

        Advisor advisor = new DefaultPointcutAdvisor(pc, new Seasoning());
        
        ProxyFactory factory = new ProxyFactory(new First());
        factory.addAdvisor(advisor);
        ((First)factory.getProxy()).one();
        ((First)factory.getProxy()).two();
        ((First)factory.getProxy()).two2();

        factory = new ProxyFactory(new Second());
        factory.addAdvisor(advisor);
        ((Second)factory.getProxy()).one();
        ((Second)factory.getProxy()).two();
    }
}
