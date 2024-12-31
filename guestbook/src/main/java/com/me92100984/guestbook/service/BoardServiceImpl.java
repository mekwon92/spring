package com.me92100984.guestbook.service;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me92100984.guestbook.domain.dto.BoardDto;
import com.me92100984.guestbook.domain.dto.GuestbookDto;
import com.me92100984.guestbook.domain.dto.PageRequestDto;
import com.me92100984.guestbook.domain.dto.PageResultDto;
import com.me92100984.guestbook.domain.entity.Board;
import com.me92100984.guestbook.domain.entity.Guestbook;
import com.me92100984.guestbook.repository.BoardRepository;
import com.me92100984.guestbook.repository.ReplyRepository;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

@Service
@Data
public class BoardServiceImpl implements BoardService {
  @Autowired
  private BoardRepository repository;
  @Autowired
  private ReplyRepository replyRepository;

  @Override
  public BoardDto get(Long bno) {
    return toDto((Object[])repository.getBoardByBno(bno));
  }

  @Override
  public Long register(BoardDto dto) {
    Board board = toEntity(dto);
    repository.save(board);
    return board.getBno();
  }

  @Override
  public PageResultDto<BoardDto, Object[]> list(PageRequestDto dto) {
    Pageable pageable = dto.getPageable(Sort.by(Direction.DESC, "bno"));
    // BooleanBuilder booleanBuilder = getSearch(dto);
    // Page<Object[]> page = repository.getBoardWithReplyCount(pageable);
    Page<Object[]> page = repository.searchPage(dto.getType(), dto.getKeyword(), pageable);
    Function<Object[], BoardDto> fn = e -> toDto(e);
    PageResultDto<BoardDto, Object[]> resultDto = new PageResultDto<>(page, fn);
    return resultDto;
  }

  @Override
  public void modify(BoardDto dto) {
    repository.save(toEntity(dto));
    
  }

  @Override
  @Transactional //트랜잭션 꼭 명시해줘야함!
  public void remove(Long bno) {
    replyRepository.deleteByBoardBno(bno);
    repository.deleteById(bno);
    
  }

  
}
