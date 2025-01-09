package com.me92100984.club.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
  @GetMapping("/")
  public String index() {
      return "redirect:/sample/all";
  }
  
  
}
