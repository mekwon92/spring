package com.me92100984.club.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.me92100984.club.entity.Note;
//이메일을 가지고 mno를 찾아다가 작성글을 가져와야한다.



@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
  Note findByNum(Long num); //id로 찾으면 optional이다? 
  
  void deleteByNum(Long num);
  
  List<Note> findByMemberMno(Long mno); 
  
  List<Note> findByMemberEmail(String email);
  
  // 목록조회시 쿼리를 너무많이 날림
  // -해결법-
  // 1. JPQL (지금사용)
  // 2. QueryDSL
  // 3. 캐싱: 조회빈도 높지만 IO가 적은 경우 사용 - @는 스프링에서만 사용가능.전용DB필요(인메모리 주로 redis 사용)
  //    redis -> 메모리?
  //    mongoDB -> 파일시스템?

  //JPQL
  @Query("select n ,count(distinct l) as likescnt, count(distinct a) as attachcnt from tbl_note n left join tbl_likes l on l.note.num = n.num left join tbl_attach a on a.note.num = n.num where n.member.email = :email group by n")
  //NativeQuery -> 이건 배열로 나옴;ㄷ
  // @Query(value = "select n.*, count (DISTINCT l.note_num, l.member_mno) likescnt, count(DISTINCT uuid) attachcnt from tbl_note n \r\n" + //
  //       "\tleft join tbl_member m on m.mno = n.member_mno and email = :email \r\n" + //
  //       "\tleft join tbl_likes l on l.note_num = n.num  \r\n" + //
  //       "\tleft join tbl_attach  a on a.note_num = n.num  \r\n" + //
  //       "\tgroup by n.num", nativeQuery = true)
  List<Object[]> findNotesBy(@Param("email") String email);


  @Query("select n ,count(distinct l) as likescnt, count(distinct a) as attachcnt from tbl_note n left join tbl_likes l on l.note.num = n.num left join tbl_attach a on a.note.num = n.num group by n")
  List<Object[]> findNotes();
}
