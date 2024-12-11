package com.me92100984.aop.ex03.adv;

import org.springframework.aop.ThrowsAdvice;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ThrowLog implements ThrowsAdvice {
    //오버로드안되고 직접이름정해줘야함?검색
    public void afterThrowing(Exception ex) {
        log.info("예외 발생 :: " + ex.getMessage());
    }
}
