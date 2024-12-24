package com.me92100984.guestbook.domain.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.me92100984.guestbook.domain.entity.GuestbookEntity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class GuestbookViewDto {
  private Long gno;
  private String title;
  private String content;
  private String writer;
  private LocalDateTime regDate;
  private LocalDateTime modDate;


  public GuestbookViewDto(Optional<GuestbookEntity> opt) {
    if(!opt.isPresent()){
      return;
    }
    GuestbookEntity entity = opt.get();
    gno = entity.getGno();
    title = entity.getTitle();
    content = entity.getContent();
    writer = entity.getWriter();
    regDate = entity.getRegDate();
    modDate = entity.getModDate();
  }
}
