package com.me92100984.member_post2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;



@Controller
@Log4j2
public class CommonController {

  @GetMapping({"/","/index"})
  public String index() {
    log.info("index controller");
    return "common/index";
  }
}
