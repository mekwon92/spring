package com.me92100984.club.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.me92100984.club.security.filter.ApiCheckFilter;
import com.me92100984.club.security.filter.ApiLoginFilter;
import com.me92100984.club.security.handler.ApiLoginFailHandler;
import com.me92100984.club.security.handler.LoginSuccessHandler;
import com.me92100984.club.security.util.JWTUtil;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {
  @Autowired
  private UserDetailsService userDetailsService;

  @Bean // 버전차이때문에 수동제어 해줘야함 -> 기존 default가 안되는 문제발생... 생성관리 순서가 엄격함...ㅠ-ㅠ
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
    AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
    builder.userDetailsService(userDetailsService)
      .passwordEncoder(passwordEncoder()).setBuilder(builder);

    AuthenticationManager authenticationManager = builder.build();
    return authenticationManager;

  }

  @Bean
  public ApiCheckFilter apiCheckFilter() {
    return new ApiCheckFilter("/api/v1/**", jwtUtil());
  }

  @Bean
  public JWTUtil jwtUtil() {
    return new JWTUtil();
  }

  @Bean 
  public ApiLoginFilter apiLoginFilter(AuthenticationManager authenticationManager) throws Exception{
    ApiLoginFilter apiLoginFilter = new ApiLoginFilter("/api/login", jwtUtil());
    apiLoginFilter.setAuthenticationManager(authenticationManager);
    apiLoginFilter.setAuthenticationFailureHandler(new ApiLoginFailHandler());
    return apiLoginFilter;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (필요에 따라 활성화)
      .authorizeHttpRequests(auth -> auth
          .requestMatchers("/sample/all").permitAll() // `/public/` 경로는 인증 없이 접근 가능
          .requestMatchers("/sample/member").hasRole("USER")
          // // .requestMatchers("/sample/admin").hasRole("ADMIN")
          .anyRequest().authenticated() // 나머지는 인증 필요
      )
      .formLogin(f -> f.permitAll()) // 기본 로그인 폼 활성화
      .userDetailsService(userDetailsService)
      .logout(l -> l.logoutUrl("/member/signout"))
      .oauth2Login(o -> o.successHandler(loginSuccessHandler()))
      // .rememberMe(r -> r.tokenValiditySeconds(60 * 60 * 24 * 14)
      // .rememberMeCookieName("remember-id"))
      ;

    http
      .addFilterBefore(apiCheckFilter(), UsernamePasswordAuthenticationFilter.class)
      .addFilterBefore(apiLoginFilter(authenticationManager(http)), UsernamePasswordAuthenticationFilter.class)
    ;
    return http.build();
  }

  @Bean
  public LoginSuccessHandler loginSuccessHandler() {
    return new LoginSuccessHandler(passwordEncoder());
  }
}