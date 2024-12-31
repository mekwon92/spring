package com.me92100984.guestbook.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.me92100984.guestbook.domain.entity.Board;
import com.me92100984.guestbook.domain.entity.Member;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
  @Autowired
  private BoardRepository repository;

  @Test
  public void testExist() {
    log.info(repository);
  }

  @Test
  @Transactional
  // @Rollback(false)
  public void testInsert() {
    IntStream.rangeClosed(2, 100).forEach(i -> {
      Board board = Board.builder()
      .title("title" + i)
      .content("content"+ "1234")
      .member(Member.builder().email("user"+i+"@a.com").build())
      .build();
      repository.save(board);
    });
  }


  @Test
  public void testGetBoardWithMember() {
    Object result = repository.getBoardWithMember(2L);
    Object[] arr = (Object[]) result;
    log.info(Arrays.toString(arr));
  }

  @Test
  public void testGetBoardWithReply() {
    List<Object[]> result = repository.getBoardWithReply(3L);
    result.forEach(arr -> log.info(Arrays.toString(arr)));
  }

  @Test
  public void testetBoardWithReplyCount() {
    Pageable pageable = PageRequest.of(0,10,Sort.by(Direction.DESC,"bno"));
    Page<Object[]> result = repository.getBoardWithReplyCount(pageable);
    result.forEach(arr -> log.info(Arrays.toString(arr)));
  }

  @Test
  public void testgetBoardByBno() {
    Object[] arr = (Object[])repository.getBoardByBno(2L);
    log.info(Arrays.toString(arr));
  }

  @Test
  @Transactional// sql을 2번 처리함
  public void testSelectOne() {
    Board board = repository.findById(2L).get();
    log.info(board);
    log.info(board.getMember()); //transactional없을 때 오류뜸. 필요할 때 2번처리..? 

  }

  @Test
  public void testSearch1() {
    repository.search1();
  }

  @Test
  public void testSearchPage() {
    Pageable pageable = PageRequest.of(0,10,Sort.by(Direction.DESC, "bno", "title"));
    repository.searchPage("TW", "10" , pageable);


  }
}
