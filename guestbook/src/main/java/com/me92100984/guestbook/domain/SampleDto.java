package com.me92100984.guestbook.domain;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;


//dto : controller <->service
@Data
@Builder(toBuilder = true)
public class SampleDto {
  private Long sno;
  private String first;
  private String last;
  private LocalDateTime regtime;
}
