package com.me92100984.guestbook.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SampleDto {
  private Long sno;
  private String first;
  private String last;
  private LocalDateTime regtime;
}
