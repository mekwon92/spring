package com.me92100984.member_post.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//어노테이션 직접 만들기
@Target(ElementType.METHOD) // 어노테이션을 어디에 적용할지(메소드에 적용)
@Retention(RetentionPolicy.RUNTIME) // 언제까지 유지될지(RUNTIME: 런타임까지) - CLASS: 클래스 파일에 포함. 런타임X / SOURCE: 컴파일 전. 클래스 파일X
public @interface MyPost { //@interface를 통해 annotation 직접 지정
  String value() default "";
  
}
