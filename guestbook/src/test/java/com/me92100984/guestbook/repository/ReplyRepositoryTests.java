package com.me92100984.guestbook.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.me92100984.guestbook.domain.entity.Board;
import com.me92100984.guestbook.domain.entity.Reply;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {
  @Autowired
  private ReplyRepository repository;

  @Test
  public void testExist() {
    log.info(repository);
  }

  @Test
  @Transactional
  // @Rollback(false)
  public void testInsert() {
    IntStream.rangeClosed(1, 500).forEach(i -> {
      Reply reply = Reply.builder()
      .text("text"+ "1234")
      .replyer("replyer" + i)
      .board(Board.builder().bno(0L+((int)(Math.random() * 99)+1)).build())
      .build();
      repository.save(reply);
    });
  }


  @Test
  public void testSelectlist() {
  }

  @Test //top-down과 bottom-up의 차이
  public void testSelectOne() {
    Reply reply = repository.findById(1L).orElse(null);
    log.info(reply);

    //댓글의 작성글의 작성자 이름 출력 - eager 상태임.
    //연관된 모든것을 불러오면 부하가 심함 -> lazy로 옵션을 줘야 함.
    log.info(reply.getBoard().getMember().getName());
  }

  @Test
  public void testUpdate() {

  }

  @Test
  public void testDelete() { 

  }

  @Test
  @Transactional
  @Rollback(false)
  public void testDeleteByBno() {
    repository.deleteByBoardBno(3L);
  }


}
