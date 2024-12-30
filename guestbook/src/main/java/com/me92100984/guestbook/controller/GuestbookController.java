package com.me92100984.guestbook.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me92100984.guestbook.domain.dto.GuestbookDto;
import com.me92100984.guestbook.domain.dto.PageRequestDto;
import com.me92100984.guestbook.service.GuestbookService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("guestbook")
@Log4j2
public class GuestbookController {
  @Inject //autowired와 탐색방법에 차이가 있긴한데 비슷함
  private GuestbookService service;

  @GetMapping({"","/","list"})
  public String list(Model model, PageRequestDto dto) {
    // log.info("list"); aspect before로 해보자.
    model.addAttribute("result", service.list(dto));
    return "guestbook/list";
  }

  @GetMapping("register")
  public void register() {

  }

  @PostMapping("register")
  public String register(GuestbookDto dto, RedirectAttributes rttr) {

    rttr.addFlashAttribute("msg", service.write(dto));
      return "redirect:list";
  }
  
  @GetMapping({"read", "modify"})
  public void read(Long gno, Model model, @ModelAttribute("pageDto") PageRequestDto pageDto) {
    model.addAttribute("dto", service.read(gno));
  }

  @PostMapping("modify")
  public String modify(GuestbookDto dto, PageRequestDto pageDto, RedirectAttributes rttr) {
    service.modify(dto);
    rttr.addAttribute("page", pageDto.getPage());
    rttr.addAttribute("type", pageDto.getType());
    rttr.addAttribute("keyword", pageDto.getKeyword());
    return "redirect:list";
  }
  
  @PostMapping("remove")
  public String remove(GuestbookDto dto, PageRequestDto pageDto, RedirectAttributes rttr) {
    service.remove(dto.getGno());
    rttr.addAttribute("page", 1); //삭제 후 1페이지로 이동
    rttr.addAttribute("type", pageDto.getType());
    rttr.addAttribute("keyword", pageDto.getKeyword());
    return "redirect:list";
  }
}
