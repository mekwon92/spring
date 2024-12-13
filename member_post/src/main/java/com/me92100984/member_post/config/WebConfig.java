package com.me92100984.member_post.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(@SuppressWarnings("null") ResourceHandlerRegistry registry) {
    registry
      .addResourceHandler("/**")
      .addResourceLocations("classpath:/static");
  }

  
}
