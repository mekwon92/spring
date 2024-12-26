package com.me92100984.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class GuestbookServiceTests {

  @Autowired
  private GuestbookService service;
  
  @Test
  public void testList() {
    log.info(service.list());
    
  }
  
}
