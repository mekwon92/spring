package com.me92100984.member_post.aop;

import java.io.IOException;
import java.net.URLEncoder;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.me92100984.member_post.vo.Member;
import com.me92100984.member_post.vo.Post;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@Aspect
@Component
@AllArgsConstructor
public class AuthAspect {
  private HttpSession session;
  private HttpServletResponse resp;
  
  @Before("@annotation(com.me92100984.member_post.aop.Mypost)")
  public void myPost(JoinPoint joinPoint, Post post) {
    Object o = session.getAttribute("member");
    if(o == null || !((Member)o).getId().equals(post.getWriter())){
      throw new RuntimeException("본인 게시글 아님");
    }
  }

  @Before("@annotation(com.me92100984.member_post.aop.SigninCheck)")
  public void SigninCheck(JoinPoint jp) throws IOException {
    if(session.getAttribute("member") == null) {
      resp.sendRedirect("/msg?msg="+URLEncoder.encode("로그인이 필요한 페이지입니다", "utf-8") + "&url=/member/signin");

    }
  }
}
