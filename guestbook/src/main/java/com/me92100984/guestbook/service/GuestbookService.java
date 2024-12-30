package com.me92100984.guestbook.service;

import java.util.List;

import com.me92100984.guestbook.domain.dto.GuestbookDto;
import com.me92100984.guestbook.domain.dto.PageRequestDto;
import com.me92100984.guestbook.domain.dto.PageResultDto;
import com.me92100984.guestbook.domain.entity.Guestbook;

public interface GuestbookService {
  // void write(GuestbookWriteDto dto);
  Long write(GuestbookDto dto);

  // List<GuestbookListDto> list();
  PageResultDto<GuestbookDto, Guestbook> list(PageRequestDto dto);

  // GuestbookViewDto get(Long gno);
  GuestbookDto read(Long gno);

  // Long modify(GuestbookModifyDto dto);
  void modify(GuestbookDto dto);
  
  void remove(Long gno);




  
  // interface에서 default나 static으로 구상메서드 작성가능
  // dto >> entity
  default Guestbook toEntity(GuestbookDto dto) {
    return Guestbook.builder()
    .gno(dto.getGno())
    .title(dto.getTitle())
    .content(dto.getContent())
    .writer(dto.getWriter())
    .build();
  }

  // entity >> dto 변환
  // dto는 화면쪽에서 사용하는거라 시간정보 필요
  default GuestbookDto toDto(Guestbook en) {
    return GuestbookDto.builder()
    .gno(en.getGno())
    .title(en.getTitle())
    .content(en.getContent())
    .writer(en.getWriter())
    .regDate(en.getRegDate())
    .modDate(en.getModDate())
    .build();
  }
}
