package com.me92100984.club.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.me92100984.club.dto.LikesDTO;
import com.me92100984.club.entity.Member;
import com.me92100984.club.entity.Note;
import com.me92100984.club.repository.MemberRepository;
import com.me92100984.club.repository.NoteRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class LikesServiceTests {

  @Autowired
  private LikesService service;  

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private NoteRepository noteRepository;

  @Test
  public void testGet() {
    service.get(LikesDTO.builder().mno(100L).num(15L).build());
  }

  @Test
  public void testToggle() {
    service.toggle(LikesDTO.builder().mno(100L).num(15L).build());
  }

  @Test
  public void testInsertDummy () {
    List<Long> mnos = new ArrayList<>(memberRepository.findAll().stream().map(Member::getMno).toList());
    List<Long> nums = new ArrayList<>(noteRepository.findAll().stream().map(Note::getNum).toList());

    Collections.shuffle(mnos);
    Collections.shuffle(nums);

    List<LikesDTO> likesDTOs = new ArrayList<>();

    
    // mnos.subList(0, (int)(mnos.size() * 2));
    for(int i = 0; i < mnos.size(); i++) {
      for(int j = 0; j < nums.size(); j++) {
        likesDTOs.add(LikesDTO.builder().mno(mnos.get(i)).num(nums.get(j)).build());
      }
    }
    log.info(likesDTOs.size());
    
    likesDTOs = likesDTOs.subList(0, likesDTOs.size()/5);

    log.info(likesDTOs.size());

    likesDTOs.forEach(dto -> service.toggle(dto));

  }


}
