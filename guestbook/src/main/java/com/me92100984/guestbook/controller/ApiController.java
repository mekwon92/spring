package com.me92100984.guestbook.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("api/todo")
// @CrossOrigin(origins = "http://localhost:3000") //CORS해결
public class ApiController{
  @GetMapping("list")
  public List<?> todo() throws Exception {
    List<?> list = IntStream.rangeClosed(1, 3).boxed().map(i -> {
      Map<String, Object> map = new HashMap<>();
      map.put("id",1); 
      map.put("content","내용"); 
      map.put("writer","작성자"); 
      map.put("regDate",LocalDate.now());
      return map;
    }).toList();
    Thread.sleep(3000);
    return list;
  }
}
