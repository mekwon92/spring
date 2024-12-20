package com.me92100984.member_post.aop.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.me92100984.member_post.exception.NotMyPostException;
import com.me92100984.member_post.exception.UnsignedAuthException;

@ControllerAdvice
public class MyControllerAdvice {
  @ExceptionHandler({UnsignedAuthException.class,NotMyPostException.class})
  public String unsignedAuthException(Exception ex) {
    return "redirect:/msg="+ ex.getMessage() + "&url=/member/signin";
  }
}