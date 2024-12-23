package com.me92100984.member_post.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.me92100984.member_post.service.MemberService;
import com.me92100984.member_post.vo.Member;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@Log4j2
@RequestMapping("member")
@AllArgsConstructor
public class MemberController {
  private MemberService service;
  InternalResourceViewResolver resolver;

  @GetMapping("mv")
  public ModelAndView mv(ModelAndView mav) {
    mav.addObject("test", "abcd");
    mav.setViewName("common/index");
    log.info(mav);
    return mav;
  }
  

  @RequestMapping(value = {"","*"}, method=RequestMethod.GET) 
  @ResponseBody
  public Member all() {
    DispatcherServlet servlet; //안에 뭐가있나,
    log.info(resolver);
    log.info(resolver.getAttributesMap());
    log.info(resolver.getOrder());
    // ViewResolver resolver;
    return new Member();
  }
  
  @RequestMapping(value = {"","*"}, method = RequestMethod.PUT)
  @ResponseBody
  public Member all2() {
    return new Member();
  }

  //return type
  // String: '해당 위치의 jsp' prefix suffix 붙인 결과 (ex: WEB-INF/jsp/member/signin.jsp)로 forward
  // void: 접속 requestURI위치를 반환 forward


  @RequestMapping(value = "signin", method = RequestMethod.GET)
  public void signin() {} // 반환 타입이 void인 경우 Spring은 요청 경로와 동일한 이름의 뷰(예: signin.jsp 또는 signin.html)를 자동으로 찾음

  @PostMapping("signin") // remember 파라미터에 Optional<String>도 가능 ->세부조정가능
  public String postSignin(Member member,@RequestParam(required = false, value =  "remember-id") String remember, HttpSession session, RedirectAttributes rttr, HttpServletResponse resp, HttpServletRequest req) {
    log.info(remember);
    log.info(member);

    if(service.login(member.getId(), member.getPw())) {
      //성공
      // 1. 세션에 member라는 이름의 attribute
      session.setAttribute("member", service.findBy(member.getId()));

      // 1-1. 아이디 저장 시 cookie에 remember-id 지정 -yes??
      Cookie cookie = new Cookie("remember-id", member.getId());
      //cookie.setPath("/");

      if(remember != null) {
        cookie.setMaxAge(60 * 60 * 24 * 7);
      }
      else { 
        cookie.setMaxAge(0);;
      }
      resp.addCookie(cookie);
      
      // 2. redirect index
      // String redirectURL = "/";
      String url = req.getParameter("url");
      // log.info(url + ":::::::");

      if(url != null) {
        url = "/";
      }
      return "redirect:" + url; 
    }

    else {
      //실패
      // rttr.addFlashAttribute("msg", "failed");
      // return "redirect:signin?msg=failed";
      
      // rttr.addAttribute("msg", "failed");
      
      rttr.addFlashAttribute("msg", "failed");
      return "redirect:signin";
      //forward말고 redirect를 해야하는 이유
    }
  }

  @RequestMapping("logout")
  public String requestMethodName(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }

  @RequestMapping("signup")
  public void signup() {}
 
  @PostMapping("signup")
  public String postSignup(Member member) {
    service.register(member);
    return "redirect:signin";
  }
  
  
}
