package com.me92100984.club.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.me92100984.club.dto.NoteDTO;
import com.me92100984.club.entity.Member;
import com.me92100984.club.entity.Note;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class NoteServiceTests {

  @Autowired
  private NoteService service;

  @Test
  public void testGet() {
    log.info(service.get(3L));
  }

  // Long write(NoteDTO dto);
  // Note get(Long num);
  // List<Note> listByEmail(String email);
  // List<Note> listByMno(Long mno);
  // void modify(NoteDTO dto);
  // void remove(Long num);

  @Test
  @Transactional
  @Rollback(false)
  public void testWrite() {
    NoteDTO dto = NoteDTO.builder()
        .title("제목")
        .content("제목")
        .writer("user100@me92100984.com")
        .mno(100L)
        .build();
    service.write(dto);
  }

  @Test
  @Transactional
  public void testListByEmail() {
    service.listByEmail("user100@me92100984.com").forEach(log::info);
  }

  @Test
  @Transactional
  public void testListByMno() {
    service.listByMno(100L).forEach(log::info);
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testRemove() {
    service.remove(1L);
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testModify() {
    NoteDTO dto = NoteDTO.builder()
        .num(8L)
        .title("바뀐제목")
        .content("바뀐내용")
        .writer("user100@me92100984.com")
        .mno(100L)
        .build();

    service.modify(dto);

  }

  @Test
  public void testRead() {
    NoteDTO dto = service.get(15L).get();
    dto.getAttachDTOs().forEach(log::info);
    service.get(15L);
  }

  @Test
  @Transactional
  public void test() {
    service.listAll();
  }
}


