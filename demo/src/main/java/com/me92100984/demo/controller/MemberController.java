package com.me92100984.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me92100984.demo.service.MemberService;
import com.me92100984.demo.vo.Member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("member")
public class MemberController {
    @Autowired
    private MemberService service;
    //@Autowired, @Resource, @Inject 중 하나를 선택
    //의존성 주입: 스프링 컨테이너에 객체 생성주기를 맡김
    //bean을 찾으러 다님 (type에 따라).. 얘들은 noargs나 allargs 중 하나여야 됨.
    
    
    @RequestMapping("")
    public String index(Model model, HttpServletRequest req, String str, Member member, HttpSession session) {
        model.addAttribute("now", service.selectNow());
        req.setAttribute("name", "kill dong");
        model.addAttribute("str", str);
        model.addAttribute("member", member);

        //세션
        
        return "hello";
    }    
}
