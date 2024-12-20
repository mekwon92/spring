package com.me92100984.member_post.mapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.me92100984.member_post.vo.Attach;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class AttachMapperTests {
  @Autowired
  private AttachMapper mapper;
  @Test
  public void testSelectAttach(){
    String data = "2024/12/19";
    

    List<Attach> list = mapper.selectListByPath("2024/12/19" );
    list.forEach(log::info);
    log.info("=================================");
    List<File> files = new ArrayList<>(Arrays.asList(new File("c:/upload",data).listFiles()));
    //
    files.forEach(log::info);
    List<File> list2 =list.stream().map(Attach::toFile).toList();
    log.info(list2);
  }
}