package com.me92100984.member_post.controller;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;



@Controller
@Log4j2
public class CommonController {

  @GetMapping({"/","/index"})
  public String index() {
    log.info("index controller");
    return "common/index";
  }


  @RequestMapping(value = "msg")
  public String msg(@ModelAttribute("msg") String msg, @ModelAttribute("url") @Nullable String url) {
    log.info(msg);
    log.info(url);
    return "common/msg";
  }
}
