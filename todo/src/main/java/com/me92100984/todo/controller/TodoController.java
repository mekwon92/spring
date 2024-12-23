package com.me92100984.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.me92100984.todo.dto.TodoWriteDto;
import com.me92100984.todo.service.TodoService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@Log4j2
@AllArgsConstructor
public class TodoController {

 private TodoService service;

  @GetMapping("todos")
  public String list(Model model) {
    model.addAttribute("todos", service.list());
    return "todo-list";
  }

  @PostMapping("todos")
  public String write(TodoWriteDto dto) {
    log.info(dto);
    service.write(dto);
    return "redirect:todos";
  }

  @RequestMapping("todos/remove")
  public String remove(@RequestParam("id") Long id) {
    log.error(id);
    service.remove(id);
    return "redirect:/todos";
  }


  @RequestMapping("todos/modify")
  public String modify(@RequestParam("id") Long id) {
    log.info(id);
    service.modify2(id);
    return "redirect:/todos";
  }

}
