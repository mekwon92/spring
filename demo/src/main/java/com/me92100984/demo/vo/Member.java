package com.me92100984.demo.vo;

import java.lang.System.Logger.Level;
import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@Builder
//@Slf4j
//@Log4j2
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private String id;
    private String pw;
    private String name;

    //java.util.logging.Logger
    private static final Logger logger = Logger.getLogger("com.me92100984.demo.vo.Member");
    
    //java.lang.System.Logger.Level
    private static final java.lang.System.Logger logger2 = System.getLogger("com.me92100984.demo.vo.Member");

    //Log4j2
    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(Member.class);
        
    
    public static void main(String[] args) {
        Member member = Member.builder().id("abcd").pw("1234").name("새똥이").build();
        System.out.println(member);
        logger.info(member.toString());
        logger2.log(Level.INFO, member);

        //Slf4j from Lombok
        //log.info(member.toString());

        log.info(member);
    }
}
