package com.me92100984.jdbc.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.me92100984.jdbc.vo.Member;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberDaoTests {
  @Autowired
  private MemberDao dao;

  @Test
  public void testGetTime() {
    log.info(dao.getTime());
  }

  @Test
  public void testRegister() {
    Member member = Member.builder().id("abcdefg").pw("1234").name("스부").build();
    dao.register(member);
  }
}
