package com.me92100984.club.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me92100984.club.entity.Note;
//이메일을 가지고 mno를 찾아다가 작성글을 가져와야한다.

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
  Note findByNum(Long num); //id로 찾으면 optional이다? 

  void deleteByNum(Long num);

  List<Note> findByMemberMno(Long mno); 

  List<Note> findByMemberEmail(String email);
  
}
