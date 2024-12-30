package com.me92100984.guestbook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.me92100984.guestbook.domain.entity.Board;
import com.me92100984.guestbook.domain.entity.Member;

public interface BoardRepository extends JpaRepository<Board, Long>{
  @Query("select b,m from tbl_board b left join member m where b.bno = :bno") 
  //tbl_board는 entity 이름을 바꾸었기 때문에~ entity를 가리키는 것임.. member는 필드의 member이기때문에 tbl_member이 아님.. 
  // JPQL에서 ON 절을 명시하지 않아도, JPA는 엔티티 간의 매핑된 관계(@ManyToOne, @OneToMany 등)를 기반으로 자동으로 적절한 JOIN 조건을 생성 - on 생략 가능..
  Object getBoardWithMember(@Param("bno") Long bno);

  @Query("select b, r from tbl_board b left join tbl_reply r on b= r.board where bno = :bno")
        //on절이.. 특이허다..
  List<Object[]> getBoardWithReply(@Param("bno") Long bno);

  
}
