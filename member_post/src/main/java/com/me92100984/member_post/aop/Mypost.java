package com.me92100984.member_post.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
//어노테이션 직접 만들기
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mypost {
  String[] id() default "abcd";
  
}