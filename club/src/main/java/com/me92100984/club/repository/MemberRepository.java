package com.me92100984.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me92100984.club.entity.Member;


public interface MemberRepository extends JpaRepository<Member, Long> {

  Member findByEmail(String email);
  Member findByEmailAndFromSocial(String email, Boolean fromSocial);
  
} 