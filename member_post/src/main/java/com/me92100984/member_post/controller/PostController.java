package com.me92100984.member_post.controller;


import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.me92100984.member_post.aop.annotation.MyPost;
import com.me92100984.member_post.aop.annotation.SigninCheck;
import com.me92100984.member_post.dto.Criteria;
import com.me92100984.member_post.dto.PageDto;
import com.me92100984.member_post.service.PostService;
import com.me92100984.member_post.vo.Member;
import com.me92100984.member_post.vo.Post;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("post")
@Log4j2
@AllArgsConstructor
public class PostController {

  //@Autowired - @AllArgsConstructor있으니까 주석
  private PostService service;

  @GetMapping("list")
  public void list(Criteria cri, Model model) {
    model.addAttribute("posts", service.list(cri));
		model.addAttribute("pageDto", new PageDto(cri, service.count(cri)));
  }

  //forward가 기본값이라 생략하면 forward가 됨
  @GetMapping("view")
  public void view(@ModelAttribute("cri") Criteria cri, Long pno, Model model) {
    model.addAttribute("post", service.view(pno));
  }

  @GetMapping("write")
  @SigninCheck
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

  @GetMapping("modify")
  @SigninCheck
  public void modify(@RequestParam("pno") Long pno, Model model, Criteria cri, @SessionAttribute(name="member", required = false) Member member, String writer) {
    log.info(pno);
    log.info(cri);
    // Object oMember = session.getAttribute("member");
    Post post = service.findBy(pno);

    if(member == null || !member.getId().equals(post.getWriter())) {
      //log.info(member.getId());
      throw new RuntimeException("동일하지 않은 혹은 비로그인");
    }
    model.addAttribute("post", post);
    
  }

  @PostMapping("modify")
  @SigninCheck
  @MyPost
  public String postModify(Post post, Criteria cri) {
    log.info(post);
    log.info(cri);
    service.modify(post);
    return "redirect:list?" + cri.getQs2();
  }

  @RequestMapping("remove")
  public String remove(@RequestParam("pno") Long pno, Criteria cri) {
    service.remove(pno);
    return "redirect:list?" + cri.getQs2();
  }

  // @RequestMapping(value = "msg", produces = MediaType.TEXT_HTML_VALUE)
  // public String msg(HttpServletResponse resp) {
  //   resp.setContentType("text/html");
  //   return "<h1>안녕</h1>";
  // }
  
}