package com.me92100984.guestbook.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.me92100984.guestbook.domain.dto.GuestbookDto;
import com.me92100984.guestbook.domain.dto.PageRequestDto;
import com.me92100984.guestbook.domain.dto.PageResultDto;
import com.me92100984.guestbook.domain.entity.Guestbook;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class GuestbookServiceTests {

  @Autowired
  private GuestbookService service;
  
  
  @Test
  public void testWrite() {
    
    GuestbookDto dto = GuestbookDto.builder()
    .title("kk")
    .content("내용")
    .writer("작성자")
    .build();
    
    Long gno = service.write(dto);
    assertNotNull(gno);
    // service.write(dto);
    // log.info(service.write(GuestbookDto.builder().content("gg").writer("작성자").title("제목").build()));
  }

  @Test
  public void testList() {
    //service.list(new PageRequestDto()).getDtoList().forEach(log::info);

    PageRequestDto dto = PageRequestDto.builder().page(1).size(10).type("TC").keyword("%제목").build();
    PageResultDto<GuestbookDto, Guestbook> resultDto = service.list(dto); 

    log.info(dto);
    resultDto.getDtoList().forEach(log::info);



  }  
  
  
}
