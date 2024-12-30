package com.me92100984.guestbook.service;

import com.me92100984.guestbook.domain.dto.BoardDto;
import com.me92100984.guestbook.domain.entity.Board;
import com.me92100984.guestbook.domain.entity.Member;

public interface BoardService {
  default Board toEntity(BoardDto dto) {
    return Board.builder()
    .bno(dto.getBno())
    .title(dto.getTitle())
    .content(dto.getContent())
    .member(Member.builder().email(dto.getMemberEmail()).name(dto.getMemberName()).build())
    .build();
  }
  
  default BoardDto toDto(Object[] arr) {
    if(arr == null) return null;
    BoardDto.BoardDtoBuilder builder = BoardDto.builder();
    for(Object o : arr) {
      Class<?> cls = o.getClass();
      if(cls == int.class || cls == Integer.class) {
        builder.replyCnt(Integer.parseInt(o.toString()));
      }
      else if(cls == Member.class) {
        builder.memberEmail(((Member)o).getEmail());
        builder.memberName(((Member)o).getName());
      }
      else if(cls == Board.class) {
        Board b = (Board)o;
        builder.bno(b.getBno());
        builder.title(b.getTitle());
        builder.content(b.getContent());
        builder.regDate(b.getRegDate());
        builder.modDate(b.getModDate());
      }
    }
    return builder.build();
  }
}
