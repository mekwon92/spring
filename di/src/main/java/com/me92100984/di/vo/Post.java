package com.me92100984.di.vo;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// java.util.Date 1.0 >> 밀레니얼 이슈
// java.util.Calendar 1.1 >> 시간대 이슈(locale), TIMESTAMP 지원못함 나노초 지원못함
// Calendar cal = new GregorianCalendar(new Locale("ko"));
// Calendar cal = Calendar.getInstance();
// java.util.LocalDateTime 1.8
// 나노-마이크로-밀리-킬로-메가-기가-테라-페타

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long pno;
    private String title;
    private String writer;
    private LocalDateTime regdate;
    
}
