package com.me92100984.guestbook.repository;

import static org.mockito.ArgumentMatchers.isNull;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

import com.me92100984.guestbook.domain.entity.Guestbook;
import com.me92100984.guestbook.domain.entity.QGuestbook;
import com.me92100984.guestbook.repository.GuestbookRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class GuestbookRepositoryTests {
  @Autowired
  private GuestbookRepository repository;

  @Test
  public void testExist() {
    log.info(repository);
  }

  @Test
  @Transactional //DB에는반영안됨?
  public void testInsert() {
    repository.saveAll(
      IntStream.rangeClosed(1, 300).mapToObj(i -> {
        return Guestbook.builder()
        .title("제목" + i)
        .content("내용" + i)
        .writer("작성자" + (i % 10))
        .build();
      }).toList()
    );
  }

  @Test
  public void testSelect() {
    repository.findAll().forEach(log::info);
  }

  @Test
  public void testSelectOne() {
    log.info(repository.findById(1L));
  }

  @Test
  public void testUpdate() {
    Long gno = 1L;

    Optional<Guestbook> opt = repository.findById(gno);
    opt.ifPresent(entity -> {
      Guestbook modifiedEntity = Guestbook.builder()
      .gno(entity.getGno())
      .title(entity.getTitle())
      .content("변경함.222.")
      .writer(entity.getWriter())
      .build();
      repository.save(modifiedEntity);
    });
    
    // @setter하는거 노추천, final 영속화에 위배
    // opt.ifPresent(e-> {e.setContent("바꿈"); e.setWriter("바꿈"); repository.save(e);});
    // log.info(repository.findById(1L));

    //1st
    // repository.save(GuestbookEntity.builder()
    // .gno(gno)
    // .title("바뀐제목")
    // .content("바뀐내용")
    // .writer("바뀐작성자")
    // .build());


    // 2nd
    // Optional<GuestbookEntity> opt = repository.findById(gno);
    // if(!opt.isPresent()) {
    //   return;
    // }

    // GuestbookEntity entity = opt.get();
    // GuestbookEntity modifiedEntity = GuestbookEntity.builder()
    // .gno(entity.getGno())
    // .title(entity.getTitle())
    // .content("변경함..")
    // .writer(entity.getWriter())
    // .build();
    // repository.save(modifiedEntity);
  }

  @Test
  public void testQuerydsl() {
    Guestbook.GuestbookBuilder builder1 = Guestbook.builder();
    builder1.content("콘텐트");
    builder1.title("타이틀");
    Guestbook entity = builder1.build();

    Pageable pageable = PageRequest.of(0,10,Sort.by(Direction.DESC, "gno"));
    
    // q도메인관련 객체 취득 GuestbookEntity의 필드에 접근
    QGuestbook qGuestbookEntity = QGuestbook.guestbook;

    String keyword = "12";

    // where절 평가를 위한 builder호출
    BooleanBuilder builder = new BooleanBuilder();
     
    // WHERE title LIKE '%12%'`에 해당 동적 조건 생성 및 결합을 위한 BooleanBuilder 객체
    BooleanExpression expression = qGuestbookEntity.title.contains(keyword);

    // 현재 BooleanBuilder에 조건을 추가. and or를 계속 붙일수있음
    builder.and(expression).or(qGuestbookEntity.writer.contains(keyword));

    // builder.and(expression);
    // builder.or(qGuestbookEntity.writer.contains(keyword));


    // 조건과 페이징 정보를 기반으로 데이터 검색 page도 list와같은 일종의 자료형..?
    Page<Guestbook> result = repository.findAll(builder, pageable); 
    result.forEach(log::info);
  }
}