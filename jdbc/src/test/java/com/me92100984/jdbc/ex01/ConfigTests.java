package com.me92100984.jdbc.ex01;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ConfigTests {
  @Autowired
  private Config config;

  @Test
  public void testConfig() throws Exception{
    log.info(config); //Config(hikariConfig=HikariDataSource (HikariPool-1), hikariDataSource=HikariDataSource (HikariPool-1), jdbcTemplate=org.springframework.jdbc.core.JdbcTemplate@440ef8d)
    log.info(config.getHikariConfig().getDriverClassName()); //org.mariadb.jdbc.Driver
    log.info(config.getHikariConfig().getDataSource()); //null

    log.info(config.getHikariDataSource()); //HikariDataSource (HikariPool-1)
    log.info(config.getJdbcTemplate().getDataSource().getConnection()); //HikariProxyConnection@1210819761 wrapping org.mariadb.jdbc.Connection@4ba6ec50

    // Connection connection = config.getHikariConfig().getDataSource().getConnection();
    // log.info(config);
  }
}
