package com.me92100984.guestbook.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.me92100984.guestbook.domain.entity.Board;
import com.me92100984.guestbook.repository.search.SearchBoardRepository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {
  @Query("select b,m from tbl_board b left join member m where b.bno = :bno") 
  //tbl_board는 entity 이름을 바꾸었기 때문에~ entity를 가리키는 것임.. member는 필드의 member이기때문에 tbl_member이 아님.. 
  // JPQL에서 ON 절을 명시하지 않아도, JPA는 엔티티 간의 매핑된 관계(@ManyToOne, @OneToMany 등)를 기반으로 자동으로 적절한 JOIN 조건을 생성 - on 생략 가능..
  Object getBoardWithMember(@Param("bno") Long bno);

  @Query("select b, r from tbl_board b left join tbl_reply r on b= r.board where bno = :bno") //on절이.. 특이허다..
  List<Object[]> getBoardWithReply(@Param("bno") Long bno);

  @Query(value= "select count(r), b, m from tbl_board b left join member m left join tbl_reply r on b=r.board group by b", 
  countQuery = "select count(b) from tbl_board b") //nativeQuery = true : jpql이 너무힘들때 최후의 수단(단점이 있음)
  Page<Object[]> getBoardWithReplyCount(Pageable pageable);

  //회원, 게시글, 댓글갯수
  @Query("select m, b, count(r) from tbl_board b left join member m left join tbl_reply r on b=r.board where b.bno= :bno")
  Object getBoardByBno(@Param("bno") Long bno);

  
}
