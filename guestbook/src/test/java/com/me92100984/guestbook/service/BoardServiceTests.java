package com.me92100984.guestbook.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.me92100984.guestbook.domain.dto.BoardDto;
import com.me92100984.guestbook.domain.dto.GuestbookDto;
import com.me92100984.guestbook.domain.dto.PageRequestDto;
import com.me92100984.guestbook.domain.dto.PageResultDto;
import com.me92100984.guestbook.domain.entity.Guestbook;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardServiceTests {
  @Autowired
  private BoardService service;

  @Test
  @Transactional
  public void testGet() {
    Long bno = 3L;
    log.info(service.get(bno));
  }

  @Test
  @Transactional
  public void testRegister() {
    //given
    BoardDto dto = BoardDto.builder().content("TEST").memberEmail("a@b.c").title("test").build();
    //when
    Long result = service.register(dto);
    //then
    assertNotNull(result);
    //에러가 뜨는이유 moddate와 regdate 때문 (SqlSyntaxErrorException)
  }

  @Test
  @Transactional
  public void testList() {
    PageResultDto<BoardDto, Object[]> dto = service.list(PageRequestDto.builder().page(1).size(10).build());
    log.info(dto);
    dto.getDtoList().forEach(log::info);
  }  

  @Test
  @Transactional
  @Rollback(false)
  public void testModify() {
    BoardDto dto = service.get(1L);
    dto.setMemberEmail("user11@a.com");
    service.modify(dto);

  }

  @Test
  @Transactional
  @Rollback(false)
  public void testDelete() {
    service.remove(4L);
  }



}
