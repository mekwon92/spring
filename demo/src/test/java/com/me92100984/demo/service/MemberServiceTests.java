package com.me92100984.demo.service;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberServiceTests {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberService memberService2;
    @Autowired
    private MemberService memberService3;

    @Test
    public void testExist() {
        log.info(memberService);
    }
    
    @Test
    public void testSelectNow() {
        memberService.selectNow();
    }

    //셋 다 같음. service는 자동으로 싱글턴임!
    @Test
    public void testSame() {
        assertSame(memberService, memberService2);
        assertSame(memberService2, memberService3);
        assertSame(memberService3, memberService);
    }
        
}
