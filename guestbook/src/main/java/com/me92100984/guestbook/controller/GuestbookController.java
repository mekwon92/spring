package com.me92100984.guestbook.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me92100984.guestbook.domain.dto.PageRequestDto;
import com.me92100984.guestbook.service.GuestbookService;


import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;


@Controller
@RequestMapping("guestbook")
@Log4j2
public class GuestbookController {
  @Inject //autowired와 탐색방법에 차이가 있긴한데 비슷함
  private GuestbookService service;

  @GetMapping({"","/","list"})
  public String list(Model model, PageRequestDto dto) {
    model.addAttribute("result", service.list(dto));
    log.info("list");
    return "guestbook/list";
  }


}
