package com.me92100984.guestbook.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.me92100984.guestbook.domain.dto.GuestbookDto;
import com.me92100984.guestbook.domain.dto.GuestbookListDto;
import com.me92100984.guestbook.domain.dto.GuestbookModifyDto;
import com.me92100984.guestbook.domain.dto.GuestbookViewDto;
import com.me92100984.guestbook.domain.dto.GuestbookWriteDto;
import com.me92100984.guestbook.domain.dto.PageRequestDto;
import com.me92100984.guestbook.domain.dto.PageResultDto;
import com.me92100984.guestbook.domain.entity.Guestbook;
import com.me92100984.guestbook.domain.entity.QGuestbook;
import com.me92100984.guestbook.repository.GuestbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@AllArgsConstructor
@Log4j2
public class GuestbookServiceImpl implements GuestbookService {
  private GuestbookRepository repository;
  
  @Override
  public Long write(GuestbookDto dto) {
    // repository.save(dto.toEntity());
    Guestbook guestbook = toEntity(dto);
    log.info(guestbook);
    repository.save(guestbook);
    log.info(guestbook);
    return guestbook.getGno();
  }

  // @Override
  // public List<GuestbookListDto> list() {
  //   return repository.findAll().stream().map(GuestbookListDto::new).toList();
  // }
  
    @Override
    public PageResultDto<GuestbookDto, Guestbook> list(PageRequestDto dto) {
      Pageable pageable = dto.getPageable(Sort.by(Direction.DESC, "gno"));
      BooleanBuilder booleanBuilder = getSearch(dto);
      Page<Guestbook> page = repository.findAll(booleanBuilder, pageable);
      // Function<Guestbook, GuestbookDto> fn = e -> toDto(e);
      PageResultDto<GuestbookDto, Guestbook> resultDto = new PageResultDto<>(page, e -> toDto(e));
      return resultDto;
    }

  // @Override
  // public GuestbookViewDto get(Long gno) {
  //   Optional<Guestbook> opt = repository.findById(gno);
  //   if(!opt.isPresent()) {
  //     return null;
  //   }
  //   return new GuestbookViewDto(opt.get());
  // }
  
  
    @Override
    public GuestbookDto read(Long gno) {
      Optional<Guestbook> opt = repository.findById(gno);
      return opt.isPresent() ? toDto(opt.get()) : null;
    }
  

  // @Override
  // public void modify(GuestbookModifyDto dto) {
  //   repository.save(dto.toEntity());
  // }

  @Override
  public void modify(GuestbookDto dto) {
    repository.save(toEntity(dto));
  }

  @Override
  public void remove(Long gno) {
    repository.deleteById(gno);
  }
  
  // @Override
  // public void remove(Long pno) {
  //   repository.deleteById(pno);
  // }

  

  private BooleanBuilder getSearch(PageRequestDto requestDto) {
    String type = requestDto.getType();
    BooleanBuilder booleanBuilder = new BooleanBuilder();
    QGuestbook qGuestbook = QGuestbook.guestbook;
    BooleanExpression expression = qGuestbook.gno.gt(0L);
    booleanBuilder.and(expression);
    if(type == null || type.trim().isEmpty()) {
      return booleanBuilder;
    }
    
    BooleanBuilder conditionalBuilder = new BooleanBuilder();
    String keyword = requestDto.getKeyword();
    if(type.contains("T")) {
      conditionalBuilder.or(qGuestbook.title.contains(keyword));
    }
    if(type.contains("C")) {
      conditionalBuilder.or(qGuestbook.title.contains(keyword));
    }
    if(type.contains("W")) {
      conditionalBuilder.or(qGuestbook.title.contains(keyword));
    }
    booleanBuilder.and(conditionalBuilder);
    return booleanBuilder;
  }
  
  
}
