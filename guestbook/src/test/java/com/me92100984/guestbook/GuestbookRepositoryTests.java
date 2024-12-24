package com.me92100984.guestbook;

import static org.mockito.ArgumentMatchers.isNull;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.me92100984.guestbook.domain.entity.GuestbookEntity;
import com.me92100984.guestbook.repository.GuestbookRepository;

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
  public void testInsert() {
    repository.saveAll(
      IntStream.rangeClosed(1, 300).mapToObj(i -> {
        return GuestbookEntity.builder()
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

    Optional<GuestbookEntity> opt = repository.findById(gno);
    opt.ifPresent(entity -> {
      GuestbookEntity modifiedEntity = GuestbookEntity.builder()
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
}
