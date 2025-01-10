package com.me92100984.club.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me92100984.club.security.dto.AuthMemberDto;


@Controller
@Log4j2
@RequestMapping("sample")
public class SampleController {
  // UsernamePasswordAuthenticationToken token;
  // AuthenticationManager manager;
  // AuthenticationProvider provider;

  @GetMapping("all")
  public void exAll(@AuthenticationPrincipal AuthMemberDto dto) {
    log.info(dto);
    log.info("ex all");
  }
  
  @GetMapping("member")
  public void exMember(@AuthenticationPrincipal AuthMemberDto dto) {
    log.info(dto);
    log.info("ex member");
  }
  
  @GetMapping("admin")
  @PreAuthorize("hasRole('ADMIN')") //config말고 여기서도 줄수이따!
  public void exAdmin(@AuthenticationPrincipal AuthMemberDto dto) {
    log.info(dto);
    log.info("ex admin");
  }

  @GetMapping("api")
  // @PreAuthorize("isAuthenticated()")
  @PreAuthorize("isAnonymous()") //비회원 사용자만 접급가능
  @ResponseBody
  public AuthMemberDto getMethodName(@AuthenticationPrincipal AuthMemberDto dto) {
      return dto;
  }

  @GetMapping("exMemberOnly") // 정해진 유저만 접근 가능
  @ResponseBody
  @PreAuthorize("#dto != null and #dto.username == 'user100@me92100984.com'")
  public String exMemberOnly(@AuthenticationPrincipal AuthMemberDto dto) {
      log.info(dto.getName());
      return dto.getEmail();
  }
  
  
}
