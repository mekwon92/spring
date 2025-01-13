package com.me92100984.club.security.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

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
          return; //기존동작 방지

        }
        filterChain.doFilter(request, response);
  }

}