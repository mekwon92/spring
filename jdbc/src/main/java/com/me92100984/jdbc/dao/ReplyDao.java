package com.me92100984.jdbc.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ReplyDao {
  private JdbcTemplate jdbcTemplate;

  public int updateToWriterNull(String id) {
    return jdbcTemplate.update("update tbl_reply set writer = NULL where writer = ?", id);
  }
}
