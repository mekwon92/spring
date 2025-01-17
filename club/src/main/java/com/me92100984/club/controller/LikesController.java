package com.me92100984.club.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me92100984.club.dto.LikesDTO;
import com.me92100984.club.service.LikesService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
  @PostMapping
  public void toggle(@RequestBody LikesDTO dto) {
      service.toggle(dto);
  }
  
}
