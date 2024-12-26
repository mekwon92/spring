package com.me92100984.guestbook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.me92100984.guestbook.domain.dto.GuestbookListDto;
import com.me92100984.guestbook.domain.dto.GuestbookModifyDto;
import com.me92100984.guestbook.domain.dto.GuestbookViewDto;
import com.me92100984.guestbook.domain.dto.GuestbookWriteDto;
import com.me92100984.guestbook.domain.entity.Guestbook;
import com.me92100984.guestbook.repository.GuestbookRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GuestbookServiceImpl implements GuestbookService {
  private GuestbookRepository repository;

  @Override
  public GuestbookViewDto get(Long gno) {
    Optional<Guestbook> opt = repository.findById(gno);
    if(!opt.isPresent()) {
      return null;
    }
    return new GuestbookViewDto(opt.get());
  }

  @Override
  public List<GuestbookListDto> list() {
    return repository.findAll().stream().map(GuestbookListDto::new).toList();
  }

  @Override
  public void modify(GuestbookModifyDto dto) {
    repository.save(dto.toEntity());
  }

  @Override
  public void remove(Long pno) {
    repository.deleteById(pno);
  }

  @Override
  public void write(GuestbookWriteDto dto) {
    repository.save(dto.toEntity());
  }
  
  
}
