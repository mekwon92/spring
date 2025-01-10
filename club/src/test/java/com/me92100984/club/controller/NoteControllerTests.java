package com.me92100984.club.controller;


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

@SpringBootTest
@AutoConfigureMockMvc
public class NoteControllerTests {

  @Autowired
  private MockMvc mockMvc;

  // @Test
  // public void testList() throws Exception {
  //   mockMvc.perform(get("/api/v1/notes/list?email=user100@me92100984.com"))
  //   .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
  // }
  
}
