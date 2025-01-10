package com.me92100984.club.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me92100984.club.dto.NoteDTO;
import com.me92100984.club.entity.Note;
import com.me92100984.club.repository.NoteRepository;

import lombok.Data;

@Service
@Data
public class NoteServiceImpl implements NoteService {

  @Autowired
  private NoteRepository repository;

  @Override
  @Transactional
  public NoteDTO get(Long num) {
    return toDTO(repository.findByNum(num));
  }

  @Override
  public List<NoteDTO> listByMno(Long mno) {
    return repository.findByMemberMno(mno).stream().map(note -> toDTO(note)).collect(Collectors.toList());
  }

  @Override
  public List<NoteDTO> listByEmail(String email) {
    return repository.findByMemberEmail(email).stream().map(note -> toDTO(note)).collect(Collectors.toList());
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
  @Transactional
  public Long write(NoteDTO dto) {
    Note note = toEntity(dto);
    repository.save(note);
    return note.getNum();
  }
}
