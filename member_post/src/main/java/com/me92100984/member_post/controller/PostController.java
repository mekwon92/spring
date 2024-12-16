package com.me92100984.member_post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.me92100984.member_post.dto.Criteria;
import com.me92100984.member_post.dto.PageDto;
import com.me92100984.member_post.service.PostService;
import com.me92100984.member_post.vo.Post;

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("post")
@Log4j2
public class PostController {

  @Autowired
  private PostService service;

  @GetMapping("list")
  public void list(Criteria cri, Model model) {
    model.addAttribute("posts", service.list(cri));
		model.addAttribute("pageDto", new PageDto(cri, service.count(cri)));
  }

  @GetMapping("view")
  public void view(@ModelAttribute("cri") Criteria cri, Long pno, Model model) {
    model.addAttribute("post", service.view(pno));
  }

  @GetMapping("write")
  public void write(@ModelAttribute("cri") Criteria cri, Post post) {
    log.info(cri);
    log.info(post);
  }
 
  @PostMapping("write")
  public String postWrite(Post post, Criteria cri) {
    post.setCno(cri.getCategory());
    service.write(post);
    return "redirect:list?"+ cri.getQs2();
  }
}
