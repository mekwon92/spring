package com.me92100984.guestbook.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
//static: MockMvcRequestBuilders.get -> get (편함) 


//controller test하기

//@WebMvcTest() 단순 controller만 챙겨옴. 다른 빈은 수동작업필요(복잡)
@SpringBootTest //전역
@AutoConfigureMockMvc
public class BoardControllerTests {

  // mock:테스트를 목적으로 사용되는 객체 Bean과는 다름
  @Autowired
  private MockMvc mockMvc;

  // @BeforeEach
  // public void init(WebApplicationContext ctx) {
  //   mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
  // }

  @Test
  public void testTest() throws Exception {
    mockMvc.perform(get("/api/v1/board/test"))
    .andExpect(status().isOk())
    // .andExpect(content().string("test1"));
    .andExpect(content().string("test"));
  }

  @Test
  public void testList() throws Exception  {
    mockMvc.perform(get("/api/v1/board/list")
    .param("page", "3")
    .param("size","5")
    .param("type","TC")
    .param("keyword","8")
    )
    .andExpect(status().isOk())
    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testRegister() throws Exception  {
    String jsonStr = "" + // 
    "{\r\n" + //
    "    \"title\": \"컨트롤러 테스트 작성\",\r\n" + //
    "    \"content\": \"등록\",\r\n" + //
    "    \"memberEmail\": \"user9@a.com\"\r\n" + //
    "}";

    mockMvc.perform(post("/api/v1/board")
    .content(jsonStr)
    .contentType(MediaType.APPLICATION_JSON_VALUE))
    .andDo(print());
  }

}


