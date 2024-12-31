package com.me92100984.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me92100984.guestbook.domain.entity.Member;
import com.me92100984.guestbook.domain.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
  void deleteByBoardBno(Long bno);

  
}
