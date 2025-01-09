package com.me92100984.club.security.dto;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter //dto는 setter도 필요하겟지?
@ToString
public class AuthMemberDto extends User {
  private Long mno;
  private String email;
  private String name;
  private boolean fromSocial;

  public AuthMemberDto(String username, String password, Long mno, boolean fromSoicial, String name, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.email = username;
    this.fromSocial = fromSoicial;
    this.name = name;
    this.mno = mno;
  }
  
}
