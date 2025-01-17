package com.me92100984.club.service;

import java.time.LocalDateTime;

import com.me92100984.club.dto.LikesDTO;
import com.me92100984.club.entity.Likes;
import com.me92100984.club.entity.Member;
import com.me92100984.club.entity.Note;

public interface LikesService {
  void toggle(LikesDTO dto);
  boolean get(LikesDTO dto);

  default LikesDTO toDTO(Likes likes) {
    return LikesDTO.builder()
      .mno(likes.getMember().getMno())
      .num(likes.getNote().getNum())
      .email(likes.getMember().getEmail())
      .modDate(likes.getModDate())
      .regDate(likes.getRegDate())
      .build();
  }

//  private Long num;
//   private Long mno;
//   private String email;
//   private LocalDateTime regDate, modDate;
  


  default Likes toEntity(LikesDTO dto) {
    return Likes.builder()
    .member(Member.builder().mno(dto.getMno()).build())
    .note(Note.builder().num(dto.getNum()).build())
    .build();
  }
}
