package com.me92100984.guestbook.service;

import java.util.Arrays;
import java.util.List;

import com.me92100984.guestbook.domain.dto.BoardDto;
import com.me92100984.guestbook.domain.dto.PageRequestDto;
import com.me92100984.guestbook.domain.dto.PageResultDto;
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
    boolean containBoard = false;

    for(Object o : arr) {
      if(o == null) continue;
      
      Class<?> cls = o.getClass();
      if(cls == Long.class || cls == long.class) {
        builder.replyCnt(Integer.parseInt(o.toString()));
      }
      else if(cls == Member.class) {
        builder.memberEmail(((Member)o).getEmail());
        builder.memberName(((Member)o).getName());
      }
      else if(cls == Board.class) {
        containBoard = true;
        Board b = (Board)o;
        builder.bno(b.getBno());
        builder.title(b.getTitle());
        builder.content(b.getContent());
        builder.regDate(b.getRegDate());
        builder.modDate(b.getModDate());
      }
    }
    return containBoard ? builder.build() : null;
  }

  //
  Long register(BoardDto dto);

  BoardDto get(Long bno);

  PageResultDto<BoardDto, Object[]> list(PageRequestDto dto);

  void modify(BoardDto dto);

  void remove(Long bno);


}
