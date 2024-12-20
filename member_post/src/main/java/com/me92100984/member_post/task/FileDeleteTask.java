package com.me92100984.member_post.task;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.me92100984.member_post.mapper.AttachMapper;
import com.me92100984.member_post.vo.Attach;

import lombok.extern.log4j.Log4j2;

@EnableScheduling
@Log4j2
@Component
public class FileDeleteTask {
  @Autowired
  private AttachMapper mapper;

  @Scheduled(cron = "0 38 14 * * *")
  public void deleteFiles() {
    String date = "2024/12/20";

    List<Attach> files = new ArrayList<>(Arrays.asList(new File("c:/upload",date).listFiles())
    .stream()
    .map(Attach::fromFile)
    .toList()); //immutable 리스트가 됨? 가변...???? -> 괄호위치옮김...?

    List<Attach> dbs = new ArrayList<>(mapper.selectListByPath(date));//immutable하기때문에 new 해야함../ㅠ
    List<Attach> thumbs = dbs.stream().filter(Attach::isImage).map(a -> Attach.builder().uuid("t_" + a.getUuid()).build()).toList();
    
    dbs.addAll(thumbs);

    files.removeAll(dbs);

    files.stream().peek(a -> a.setPath(date)).map(Attach::toFile)
    //.forEach(File::delete); //consumer는 반환하면안됨. 밑이랑 같음.
    .forEach((file) -> {file.delete();});

    log.info(files.size() + "개의 파일 삭제");

    // stream

    //최종연산
    // forEach(consumer) ,count()

    //중간연산
    // filter(predicate: 1개 입력 boolean 반환), map(function: 1개 입력 1개 반환), peek(consumer)

    //supplier 예시(입력없으며 값 반환)
    log.info(() -> {return "abcd";});
  }
}
