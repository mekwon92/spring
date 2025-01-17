package com.me92100984.club.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder.Default;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NoteDTO {
  private Long num;
  private String title;
  private String content;
  private String writer;
  private Long mno;
  private LocalDateTime regDate, modDate;

  private long likesCnt; //entity에는 존재하면 안됨(부하생김)
  private long attachCnt; //성능향상을 위해서
  
  @Default
  private List<AttachDTO> attachDTOs = new ArrayList<>();
}
