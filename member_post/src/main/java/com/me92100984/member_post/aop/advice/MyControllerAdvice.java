package com.me92100984.member_post.aop.advice;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.me92100984.member_post.exception.NotMyPostException;
import com.me92100984.member_post.exception.UnsignedAuthException;

@ControllerAdvice // 특정 컨트롤러나 전체 컨트롤러에서 동작할 전역 처리 로직(예외 처리, 데이터 바인딩 설정, 모델 객체 추가 등)
public class MyControllerAdvice {
  @ExceptionHandler({UnsignedAuthException.class,NotMyPostException.class})//특정 예외 처리

  public String UnsignedAuthException(Exception ex) throws UnsupportedEncodingException{
    return "redirect:/msg?msg=" + URLEncoder.encode(ex.getMessage(), "utf-8")  + "&url=/member/signin";
    // 로그인되지 않은 사용자(UnsignedAuthException)나 권한이 없는 사용자(NotMyPostException)가 발생했을 때:
    // 해당 예외 메시지를 보여주고 로그인 페이지(/member/signin)로 이동하도록 리다이렉트
  }
}