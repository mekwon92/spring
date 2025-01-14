package com.me92100984.club.config;
import java.util.List;

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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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

  @Bean // spring security global Cors
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }



  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (필요에 따라 활성화)
      .authorizeHttpRequests(auth -> auth
          .requestMatchers("/sample/all").permitAll() // `/public/` 경로는 인증 없이 접근 가능
          .requestMatchers("/swagger-ui.html").permitAll() 
          .requestMatchers("/sample/member").hasRole("USER")
          // .requestMatchers("/sample/admin").hasRole("ADMIN")
          // .anyRequest().authenticated() // 나머지는 인증 필요하다!
          .anyRequest().permitAll()
      )
      .userDetailsService(userDetailsService)
      // .oauth2Login(o -> o.successHandler(loginSuccessHandler()))
      // .rememberMe(r -> r.tokenValiditySeconds(60 * 60 * 24 * 14)
      // .rememberMeCookieName("remember-id"))
      .cors(c -> c.configurationSource(corsConfigurationSource()))
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