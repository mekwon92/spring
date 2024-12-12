package com.me92100984.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "Hello World";
    }
}