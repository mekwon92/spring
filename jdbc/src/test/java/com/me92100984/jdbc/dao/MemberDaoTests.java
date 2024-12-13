package com.me92100984.jdbc.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Map;

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

  // @Test
  public void testGetTime() {
    log.info(dao.getTime());
  }

  @Test
  public void testRegister() {
    Member member = Member.builder().id("abcde").pw("1234").name("스부").build();
    dao.register(member);
  }

  @Test
  public void testModify() {
    Member member = dao.selectOne("aaa");
    log.info(member);
    member.setRoad_addr("주소바꾸기22222");
    //member = Member.builder().pw("2222").name("바보").email("me@gmail.com").build();
    dao.modify(member);
    int result = dao.modify(member);
    assertEquals(1, result);

    log.info(dao.selectOne("aaa"));
  }

  @Test
  public void testRemove() {
    assertEquals(1, dao.remove("hahaha"));
  }


  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Test
  public void testList() {
    
     dao.selectList().forEach(log::info);

    // dao.selectList().forEach(map -> log.info(map.get("id"))); //map -> cast 문제가 있다..
    // dao.selectList().forEach(map -> {
    //   if(map instanceof Map) {

    //     Object id = ((Map)map).get("id"); 
    //     if(id instanceof String) {
    //       String idStr = (String)id;
    //     }
    //     log.info(((Map)map).get("id"));
    //   }
    // });
  }

  @Test 
  void testOne() {
    log.info(dao.selectOne("abcd"));
  }


  @Test
  public void testObject() {
    Object o = new Date();

    try {
      String s = (String)o;
      Long l = Long.valueOf(s);
      log.info(l + 2000);
    } catch (ClassCastException e) {
      log.info("cast 문제");
    }
    catch (NumberFormatException e) {
      log.info("문자열이지만 숫자 아님");
    }


    log.info(o.getClass().getName());
    log.info(o instanceof String);

    // if(o.getClass() == String.class) {
    // }

  //   try {
  //     Object o = "avcd";
  //     String s = String.valueOf(o);
  //     Long l = Long.parseLong(s);
      
  //     l += 2000L;
  //     log.info(l);
      
      
  //   } catch (Exception e) {
  //     // TODO: handle exception
  //   }
  }
}
