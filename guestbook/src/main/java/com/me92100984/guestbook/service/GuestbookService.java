package com.me92100984.guestbook.service;

import java.util.List;


import com.me92100984.guestbook.domain.dto.GuestbookListDto;
import com.me92100984.guestbook.domain.dto.GuestbookModifyDto;
import com.me92100984.guestbook.domain.dto.GuestbookViewDto;
import com.me92100984.guestbook.domain.dto.GuestbookWriteDto;

public interface GuestbookService {
  void write(GuestbookWriteDto dto);
  void modify(GuestbookModifyDto dto);
  void remove(Long pno);

  List<GuestbookListDto> list();
  GuestbookViewDto get(Long gno);
}
