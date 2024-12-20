package com.me92100984.member_post.aop.advice;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.me92100984.member_post.exception.NotMyPostException;
import com.me92100984.member_post.exception.UnsignedAuthException;

@ControllerAdvice
public class MyControllerAdvice {
  @ExceptionHandler({UnsignedAuthException.class,NotMyPostException.class})
  public String UnsignedAuthException(Exception ex) throws UnsupportedEncodingException{
    return "redirect:/msg?msg=" + URLEncoder.encode(ex.getMessage(), "utf-8")  + "&url=/member/signin";
  }
}