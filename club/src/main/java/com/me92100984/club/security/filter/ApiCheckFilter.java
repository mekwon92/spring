package com.me92100984.club.security.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;

@Log4j2
@Component
public class ApiCheckFilter extends OncePerRequestFilter {

  private AntPathMatcher antPathMatcher;
  private String pattern;

  public ApiCheckFilter(String pattern) {
    antPathMatcher = new AntPathMatcher();
    this.pattern = pattern;
  }
  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
      if(antPathMatcher.match(pattern, request.getRequestURI())) {
        log.info("============== api check filter ==========="); 
        log.info(request.getRequestURI());

        if(checkAuthHeader(request)) {
          filterChain.doFilter(request, response);
        }
        else {
          response.setContentType("application/json; charset=utf-8");
          response.setStatus(403);
          JSONObject jsonObject = new JSONObject();
          jsonObject.put("code", 403);
          jsonObject.put("message", "FAIL CHECK API TOKEN");

          response.getWriter().print(jsonObject);
        }
        return; //기존동작 방지
      }
      // filterChain.doFilter(request, response); //여기로 안떨어짐
   }

   private boolean checkAuthHeader(HttpServletRequest request){
    boolean result = false;
    String authHeader = request.getHeader("Authorization");

    if(StringUtils.hasText(authHeader)) {
      log.info("Authentication exist :: " + authHeader);
      if(authHeader.equals("12345678")) {
        result = true;
      }
    }
    return result;
   }



}