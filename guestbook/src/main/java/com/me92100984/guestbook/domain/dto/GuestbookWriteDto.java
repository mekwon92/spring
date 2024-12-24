package com.me92100984.guestbook.domain.dto;

import com.me92100984.guestbook.domain.entity.GuestbookEntity;

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
public class GuestbookWriteDto {
  private String title;
  private String content;
  private String writer;

  public GuestbookWriteDto(GuestbookEntity entity) {
    title = entity.getTitle();
    content = entity.getContent();
    writer = entity.getWriter();
  }

  public GuestbookEntity toEntity() {
    return GuestbookEntity.builder().title(title).content(content).writer(writer).build();
  }
}
