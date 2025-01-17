package com.me92100984.club.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me92100984.club.dto.LikesDTO;
import com.me92100984.club.service.LikesService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("api/v1/likes")
@Log4j2
public class LikesController {
  @Autowired
  private LikesService service;

  @GetMapping
  public boolean get(LikesDTO dto){
    log.info(dto);
    return service.get(dto);
  }

  // @PreAuthorize("email == dto.email") 원래 해야함;
  @PostMapping
  public ResponseEntity<?> toggle(@RequestBody LikesDTO dto, @AuthenticationPrincipal String email) {
    log.info(dto);
    log.info(email);
    log.info(dto.getEmail());
    return ResponseEntity.ok().body(Map.of("result", service.toggle(dto)));
  }
  
}
