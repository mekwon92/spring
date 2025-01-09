package com.me92100984.club.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me92100984.club.entity.Member;
import com.me92100984.club.repository.MemberRepository;
import com.me92100984.club.security.dto.AuthMemberDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service // bean으로 등록되어있어야 감지함
public class MyMemberUserDetailsService implements UserDetailsService {

  @Autowired
  private MemberRepository repository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info(username);
    Member member = repository.findByEmailAndFromSocial(username, false);
    if(member == null) {
      throw new UsernameNotFoundException(username);
    }
    AuthMemberDto authMemberDto = new AuthMemberDto(member.getEmail(), member.getPassword(), member.getMno(), member.isFromSocial(), member.getName(), 
    member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_"+role.name())).toList());

    return authMemberDto;

  }

  
  
}
