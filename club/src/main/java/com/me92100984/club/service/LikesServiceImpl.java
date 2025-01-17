package com.me92100984.club.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me92100984.club.dto.LikesDTO;
import com.me92100984.club.entity.composite.LikesId;
import com.me92100984.club.repository.LikesRepository;
import com.me92100984.club.repository.MemberRepository;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Service
@Data
@Log4j2
public class LikesServiceImpl implements LikesService {
  @Autowired
  private LikesRepository repository;
  @Autowired
  private MemberRepository memberRepository;

  @Override
  public boolean get(LikesDTO dto) {
    if(dto.getMno() == null) {
      long mno = memberRepository.findByEmail(dto.getEmail()).getMno();
      dto.setMno(mno);
    }
    return repository.findById(new LikesId(dto)).isPresent();
  }

  @Override
  public boolean toggle(LikesDTO dto) {
    if(dto.getMno() == null) {
      long mno = memberRepository.findByEmail(dto.getEmail()).getMno();
      dto.setMno(mno);
    }
    boolean ret = get(dto);
    if(get(dto)) {
      repository.delete(toEntity(dto));
    }
    else {
      repository.save(toEntity(dto));
    }
    return ret;
  }
}
