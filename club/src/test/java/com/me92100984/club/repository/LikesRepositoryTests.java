package com.me92100984.club.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.me92100984.club.entity.Likes;
import com.me92100984.club.entity.Member;
import com.me92100984.club.entity.Note;
import com.me92100984.club.entity.composite.LikesId;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class LikesRepositoryTests {
  @Autowired
  private LikesRepository repository;

  @Test
  public void testExist() {
    log.info(repository);
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testInsert() {
    Likes likes = Likes.builder()
      .member(Member.builder().mno(100L).build())
      .note(Note.builder().num(15L).build())
      .build();
      repository.save(likes);
  }

  @Test
  public void testDelete() {
    repository.delete(Likes.builder()
    .member(Member.builder().mno(100L).build())
    .note(Note.builder().num(15L).build())
    .build());
  }
  
}
