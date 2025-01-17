package com.me92100984.club.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LikesDTO {
  private Long num;
  private Long mno;
  private String email;
  private LocalDateTime regDate, modDate;
  
}
