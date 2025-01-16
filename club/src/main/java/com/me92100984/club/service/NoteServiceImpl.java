package com.me92100984.club.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me92100984.club.dto.NoteDTO;
import com.me92100984.club.entity.Member;
import com.me92100984.club.entity.Note;
import com.me92100984.club.repository.MemberRepository;
import com.me92100984.club.repository.NoteRepository;

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

  @Override
  @Transactional
  public Optional<NoteDTO> get(Long num) {
    return repository.findById(num).map(this::toDTO);
  }

  
  @Override
  public List<NoteDTO> listAll() {
    return repository.findAll().stream().map(this::toDTO).toList();
  }


  @Override
  public List<NoteDTO> listByMno(Long mno) {
    return repository.findByMemberMno(mno).stream().map(note -> toDTO(note)).collect(Collectors.toList());
  }

  @Override
  public List<NoteDTO> listByEmail(String email) {
    return repository.findByMemberEmail(email).stream().map(this::toDTO).toList();
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
