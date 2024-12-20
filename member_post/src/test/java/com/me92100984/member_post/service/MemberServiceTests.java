package com.me92100984.member_post.service;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.me92100984.member_post.vo.Member;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberServiceTests {
  @Autowired
  MemberServiceImpl service;

  @Test
  public void testFindby(){
    log.info(service.findBy("mekwon"));
  }

  @Test
  public void testSignup() {
    Member member = Member.builder().id("test002").pw("1234").name("새똥이").build();
  }
}