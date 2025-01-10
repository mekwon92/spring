package com.me92100984.club.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteDTO {
  private Long num;
  private String title;
  private String content;
  private String writer;
  private Long mno;
  private LocalDateTime regDate, modDate;
}
