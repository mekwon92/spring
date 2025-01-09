package com.me92100984.club.repository;

import static org.mockito.ArgumentMatchers.isNull;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.me92100984.club.entity.Member;
import com.me92100984.club.entity.MemberRole;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {
  @Autowired
  private MemberRepository repository;

  @Autowired
  private PasswordEncoder encoder;

  @Test
  public void testInsert() {
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Member m = Member
      .builder()
      .email("user" + i + "@me92100984.com")
      .name("사용자" + i)
      .password(encoder.encode("1234"))
      .build();
      m.addMemberRole(MemberRole.USER);

      if( i > 80) {
        m.addMemberRole(MemberRole.MANAGER);
      }

      if( i > 90) {
        m.addMemberRole(MemberRole.ADMIN);
      }

      //repository.save(m);
      
    });
  }
  @Test
  @Transactional
  public void testFindByEmail() {
    Member m = repository.findByEmail("user100@me92100984.com");
    log.info(m);
  
  }

  @Test
  @Transactional
  public void testFindByEmailAndFromSocial() {
    Member m = repository.findByEmailAndFromSocial("user100@me92100984.com", false);
    log.info(m);
    UserDetailsService service;
    User user;
  }
}

