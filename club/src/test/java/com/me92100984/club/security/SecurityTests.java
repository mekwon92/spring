package com.me92100984.club.security;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class SecurityTests {
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  public void testEncoder() {
    log.info(passwordEncoder);
    log.info(passwordEncoder.getClass().getName());

    String pw = "1234";
    //String pw = "12345";
    String encoded = passwordEncoder.encode(pw);
    log.info(pw);
    log.info(encoded);

    // $2a$10$Pf1VFRXhQzcT7rSzjdKRhe3qrigqqiTjWgU/y25FszG4c8oIE3vlu
    // $2a$10$CYheXVHmnAHWg05ugZ.VAuP1QGyaWhJPrBSUlZcwwiiXd7txn8FZG

    assertTrue(passwordEncoder.matches(pw, "$2a$10$Pf1VFRXhQzcT7rSzjdKRhe3qrigqqiTjWgU/y25FszG4c8oIE3vlu"));
    assertTrue(passwordEncoder.matches(pw, "$2a$10$CYheXVHmnAHWg05ugZ.VAuP1QGyaWhJPrBSUlZcwwiiXd7txn8FZG"));
  }
  
}
