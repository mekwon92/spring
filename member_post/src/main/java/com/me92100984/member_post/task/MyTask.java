package com.me92100984.member_post.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class MyTask {

  public void printTime() {
    log.info(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS").format(new Date()));
  }
}
