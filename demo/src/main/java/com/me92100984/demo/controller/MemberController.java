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
    //의존성 주입: 스프링 컨테이너에 객체 생성주기를 맡김
    //DI때문에 얘는 모든걸 하는......
    //bean을 찾으러 다님 type에 따라 뭔지 찾으러다님 .. 얘들은 noargs나 allargs 중 하나여야 됨.
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
