package com.me92100984.guestbook.controller;

import java.time.LocalDateTime;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me92100984.guestbook.domain.SampleDto;

@Controller
@RequestMapping("sample")
public class SampleController {
  @GetMapping("ex02")
  public void ex02(Model model) {
    model.addAttribute("list", 
    LongStream.rangeClosed(1, 20)
    .mapToObj(i->SampleDto.builder()
    .sno(i)
    .first("first" + i)
    .last("last" + i)
    .regtime(LocalDateTime.now())
    .build()));
  }
}
