package com.me92100984.guestbook.repository.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.me92100984.guestbook.domain.entity.Board;
import com.me92100984.guestbook.domain.entity.QBoard;
import com.me92100984.guestbook.domain.entity.QReply;
import com.me92100984.guestbook.domain.entity.QMember;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {
  public SearchBoardRepositoryImpl() {
    super(Board.class);
  }  

  @Override
  public Board search1() {
    log.info("search1 ...");
    QBoard board = QBoard.board;
    QReply reply = QReply.reply;
    QMember member = QMember.member;

    JPQLQuery<Board> jpqlQuery = from(board);
    jpqlQuery
      .leftJoin(member).on(board.member.eq(member))
      .leftJoin(reply).on(reply.board.eq(board));
    JPQLQuery<Tuple> tuple =
    jpqlQuery
      .select(board, member.email, reply.count())
      .groupBy(board);
    log.info(jpqlQuery);
    List<Tuple> list = tuple.fetch();
    return null;
  }

  @Override
  public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
    QBoard board = QBoard.board;
    QMember member = QMember.member;
    QReply reply = QReply.reply;

    JPQLQuery<Board> jpqlQuery = from(board);
    jpqlQuery
      .leftJoin(member).on(board.member.eq(member))
      .leftJoin(reply).on(reply.board.eq(board));

    JPQLQuery<Tuple> tuple =
    jpqlQuery
      .select(board, member.email, reply.count());

    BooleanBuilder booleanBuilder = new BooleanBuilder();
    BooleanExpression expression = board.bno.gt(0L);

    booleanBuilder.and(expression);

    if(type != null) {
      BooleanBuilder conditionBuilder = new BooleanBuilder();

      if(type.contains("T")){
        conditionBuilder.or(board.title.contains(keyword));
      }
      if(type.contains("C")){
        conditionBuilder.or(board.content.contains(keyword));

      }
      if(type.contains("W")){
        conditionBuilder.or(member.email.contains(keyword));
      }
      booleanBuilder.and(conditionBuilder);
    }
    tuple.where(booleanBuilder);
    tuple.groupBy(board);
    List<Tuple> result = tuple.fetch();
    return null;
  }

  
}
