package com.me92100984.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me92100984.guestbook.domain.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

  
}
