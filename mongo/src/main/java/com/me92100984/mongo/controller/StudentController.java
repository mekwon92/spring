package com.me92100984.mongo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.me92100984.mongo.entity.Student;
import com.me92100984.mongo.service.StudentService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@AllArgsConstructor
public class StudentController {
  private StudentService studentService;

  @PostMapping("register")
  public void register(@RequestBody Student student) {
    studentService.register(student);

  }

  @GetMapping("/students")
  public List<Student> list() {
      return studentService.list();
  }
  
  @GetMapping("/students/{no}")
  public Student get(@PathVariable Long no) {
      return studentService.get(no).orElse(null);
  }
  
  
}
