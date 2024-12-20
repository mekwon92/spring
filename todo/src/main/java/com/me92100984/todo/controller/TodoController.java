package com.me92100984.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.me92100984.todo.dto.TodoWriteDto;
import com.me92100984.todo.service.TodoService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@Log4j2
@AllArgsConstructor
public class TodoController {

 private TodoService service;

  @GetMapping("todos")
  public String list(Model model) {
    model.addAttribute("totos", service.list());
    return "todo-list";
  }

  @PostMapping("todos")
  public String write(TodoWriteDto dto) {
    log.info(dto);
    service.write(dto);
    return "redirect:todos";
  }
  
}
