package com.me92100984.member_post;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class FileTests {
  // @Test
  // public void testDeleteFiles(){
  //   File file = new File("c:/upload/2024/12/19","3e092ab9-7442-48eb-9d8a-87f25cee9580.gif");
  //   file.delete();
  // }
  // @Test
  // public void testListFiles(){
  //   File file = new File("c:/upload/2024/12/19");
  //   log.info(file.isDirectory());
  //   log.info(file.isFile());

  //   new ArrayList<>(Arrays.asList(file.listFiles(pathname -> pathname.getName().endsWith("gif")))).forEach(log::info);
  // }
}