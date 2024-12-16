package com.me92100984.member_post.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.me92100984.member_post.dto.Criteria;

@SpringBootTest
public class PostMapperTests {
  @Autowired
  private PostMapper mapper;

  @Test
  public void testExist() {
    assertNotNull(mapper);
  }

  @Test
  public void testList() {
    mapper.selectList(new Criteria());
  }

  @Test
  public void testView() {
    mapper.selectOne(null);
  }
  
}
