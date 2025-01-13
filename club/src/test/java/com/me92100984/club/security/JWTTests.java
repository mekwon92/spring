package com.me92100984.club.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.me92100984.club.security.util.JWTUtil;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class JWTTests {
  private JWTUtil jwtUtil;

  @BeforeEach
  public void testBefore() {
    System.out.println("testBefore.........");
    jwtUtil = new JWTUtil();
  }

  @Test
  public void testCreate() throws Exception {
    String email = "user100@me92100984.com";
    String str = jwtUtil.generateToken(email);
    System.out.println(str);
    //eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MzY3NTA5ODYsImV4cCI6MTczOTQyOTM4Niwic3ViIjoidXNlcjEwMEBtZTkyMTAwOTg0LmNvbSJ9.44o3iBsywCBzAOdTnE8iFBv1V0hDaUd_w4B-XGM8Ipo
    //eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MzY3NTI3NDYsImV4cCI6MTczOTQzMTE0Niwic3ViIjoidXNlcjEwMEBtZTkyMTAwOTg0LmNvbSJ9.oXYGZV5f6I6vWbNyGGudrkwHODNtKf9veBT9QVUctSw
  }

  @Test
  public void testExtract() throws Exception {
    String email = "user100@me92100984.com"; //user1로도 테스트
    String token = jwtUtil.generateToken(email);

    Thread.sleep(5000);

    String resultEmail = jwtUtil.validateExtract(token);
    // String resultEmail = jwtUtil.validateExtract("abcd" + token + "1");

    log.info(resultEmail);

    assertEquals(email, resultEmail);
  }
  
}
