package com.me92100984.di2.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.me92100984.di2.sample.Restaurant;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class SampleTests {
  @Setter
  @Autowired
  private Restaurant restaurant;

  @Test
  public void test() {
    assertNotNull(restaurant);  
    log.info(restaurant);
    log.info("=========================");
    log.info(restaurant.getChef());
  }
}
