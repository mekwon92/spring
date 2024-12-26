package com.me92100984.guestbook.domain.dto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//dto 개선작업
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GuestbookDto {
  private Long gno;
  private String title, content, writer;
  private LocalDateTime regDate, modDate;
  
}
