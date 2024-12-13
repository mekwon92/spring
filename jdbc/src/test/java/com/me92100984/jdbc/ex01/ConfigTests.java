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

  //DataSource 
  /* JDBC에서 데이터베이스와의 연결을 관리하기 위한 핵심 인터페이스
     데이터베이스 연결을 추상화하여 애플리케이션이 보다 효율적으로 연결을 관리하고 성능을 최적화할 수 있도록 설계 */
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
