package com.me92100984.di;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import lombok.Data;

@Data
@SpringBootApplication
//컨테이너가 탐색해서 Bean이 될 수 있도록 어노테이션 지정(내가 정의한 클래스를 bean으로 만들어주세요)
//@Component, @Service, @Controller
//@ComponentScan(basePackages = "com.me92100984.di") - 스프링이 특정 패키지를 탐색하여 Bean으로 등록할 컴포넌트를 찾도록 설정, @SpringBootApplication에 기본적으로 포함(다른 경로를 탐색하려면 명시적으로 설정)
public class DiApplication {
	// @Bean 대상 메서드의 반환값을 Bean으로 만든다. 반환타입이 void면 안됨.
	// int안됨 integer됨. 객체여야함. (메인에서는 안됨ㅋ)
	public static void main(String[] args) {
		SpringApplication.run(DiApplication.class, args);
	}

}
