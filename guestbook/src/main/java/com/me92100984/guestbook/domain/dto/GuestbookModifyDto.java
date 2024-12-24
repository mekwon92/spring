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
public class GuestbookModifyDto {
  private Long gno;
  private String title;
  private String content;
  private String writer;

  public GuestbookEntity toEntity() {
    return GuestbookEntity.builder()
    .gno(gno)
    .title(title)
    .content(content)
    .writer(writer)
    .build();
  }
}
