package com.me92100984.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.me92100984.guestbook.domain.entity.GuestbookEntity;
//querydsl , QuerydslPredicateExecutor<GuestbookEntity> 
public interface GuestbookRepository extends JpaRepository<GuestbookEntity, Long> { //entity랑 pk타입

}

 
 