package com.me92100984.guestbook.domain.dto;

import java.time.LocalDateTime;

import com.me92100984.guestbook.domain.entity.Guestbook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder 
@AllArgsConstructor
@NoArgsConstructor
public class GuestbookListDto {
  private Long gno;
  private String title;
  private String writer;
  private LocalDateTime regDate;
  private LocalDateTime modDate;


  public GuestbookListDto(Guestbook entity) {
    gno = entity.getGno();
    title = entity.getTitle();
    writer = entity.getWriter();
    regDate = entity.getRegDate();
    modDate = entity.getRegDate();
  }
}
