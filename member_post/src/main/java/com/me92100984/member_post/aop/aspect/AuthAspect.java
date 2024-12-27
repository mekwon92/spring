package com.me92100984.member_post.aop.aspect;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.me92100984.member_post.aop.annotation.MyPost;
import com.me92100984.member_post.exception.NotMyPostException;
import com.me92100984.member_post.exception.UnsignedAuthException;
import com.me92100984.member_post.vo.Member;
import com.me92100984.member_post.vo.Post;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@AllArgsConstructor
@Log4j2
public class AuthAspect {
  private HttpSession session;
  private HttpServletRequest req;
  private HttpServletResponse resp;
  
  @Before("@annotation(com.me92100984.member_post.aop.annotation.SigninCheck)")
  public void SigninCheck(JoinPoint jp) throws IOException {
    // log.info(req.getRequestURL());
    // log.info(req.getRequestURI());
    // log.info(req.getQueryString());
    String url = "/member/signin?url=" + URLEncoder.encode(req.getRequestURI() + "?" + req.getQueryString(), "utf-8");
   
    if(session.getAttribute("member") == null) {
      resp.sendRedirect("/msg?msg=" + URLEncoder.encode("로그인이 필요한 페이지입니다.", "utf-8") + "&url=" + url);
    }
  }


  @Before("@annotation(com.me92100984.member_post.aop.annotation.Mypost)")
  public void myPost(JoinPoint joinPoint) throws IOException {
    Object o = session.getAttribute("member");

    if(o == null){
      throw new UnsignedAuthException("비로그인상태");
    }

    String id = ((Member)o).getId(); //현재 로그인된 사용자

    Object[] args = joinPoint.getArgs();

    for(Object obj : args){
      if(obj instanceof Post && !((Post)obj).getWriter().equals(id)){
        throw new NotMyPostException("본인 게시글 아님");
      }
    }
    log.error(Arrays.toString(args));
    log.error(id);
  }
}
