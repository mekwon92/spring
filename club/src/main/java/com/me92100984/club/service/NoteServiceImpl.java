package com.me92100984.club.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me92100984.club.dto.NoteDTO;
import com.me92100984.club.entity.Member;
import com.me92100984.club.entity.Likes;
import com.me92100984.club.entity.Note;
import com.me92100984.club.repository.MemberRepository;
import com.me92100984.club.repository.NoteRepository;
import com.me92100984.club.repository.LikesRepository;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Service
@Data
@Log4j2

public class NoteServiceImpl implements NoteService {

  @Autowired
  private NoteRepository repository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private LikesRepository likesRepository;

  @Override
  @Transactional
  public Optional<NoteDTO> get(Long num) {
    long count = likesRepository.count(Example.of(Likes.builder().note(Note.builder().num(num).build()).build()));
    log.info(count);
    return repository.findById(num).map(this::toDTO).map(d -> { d.setLikesCnt(count); return d; });
  }
  
  @Override
  public List<NoteDTO> listAll() {
    return repository.findNotes().stream().map(o -> {
      log.info(Arrays.toString(o));
      NoteDTO dto = toDTO((Note)o[0]); 
      dto.setLikesCnt((Long)o[1]);
      dto.setAttachCnt((Long)o[2]);

      return dto;
      }).toList();
  }

  @Override
  public List<NoteDTO> listByMno(Long mno) {
    return repository.findByMemberMno(mno).stream().map(note -> toDTO(note)).collect(Collectors.toList());
    //  return repository.findByMemberEmail(email).stream().map(this::toDTO).toList();
  }

  @Override
  public List<NoteDTO> listByEmail(String email) {
    return repository.findNotesBy(email).stream().map(o -> {
      NoteDTO dto = toDTO((Note)o[0]); 
      dto.setLikesCnt((Long)o[1]);
      dto.setAttachCnt((Long)o[2]);
      return dto;
      }).toList();
  }

  @Override
  public int modify(NoteDTO dto) {
    repository.save(toEntity(dto));
    return 1;
  }

  @Override
  @Transactional
  public int remove(Long num) {
    repository.deleteByNum(num);
    return 1;
  }

  @Override
  public Long write(NoteDTO dto) {
    Member member = memberRepository.findByEmail(dto.getWriter());
    log.info(member);
    log.info(member.getMno());
    dto.setMno(member.getMno());
    return repository.save(toEntity(dto)).getNum();
  }
}
