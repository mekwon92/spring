package com.me92100984.guestbook.aop;

import java.util.stream.Stream;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Aspect
@Log4j2
@Component //aspect는 bean이 되어야함.
public class ControllerLogging {
  @Before("execution(* com.me92100984.guestbook.controller..*(..))") //before는 void가능 around는 object를 반환해야됨.
  public void log(JoinPoint jp) {
    String className = jp.getTarget().getClass().getSimpleName();
    String methodName = jp.getSignature().getName();

    Object[] args = jp.getArgs();
    
    String paramTypes = String.join(", ", Stream.of(args).map(a -> a != null ? a.getClass().getSimpleName() : "").toList());
    String str = String.format("%s.%s(%s)", className, methodName, paramTypes);
    log.info("============================== controller logger start =====================================");
    log.info(str);
    Stream.of(args).filter(o -> {
      String name = o.getClass().getName();
      return name.contains("com.me92100984") || name.contains("java.lang");
    }).forEach(log::info);
    log.info("============================== controller logger end =======================================");
  }
}
