package com.me92100984.todo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.me92100984.todo.dto.TodoListDto;
import com.me92100984.todo.dto.TodoWriteDto;
import com.me92100984.todo.repository.TodoRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class TodoService {
  private TodoRepository repository;
  // 목록
  public List<TodoListDto> list() {
    return repository.findAll().stream().map(TodoListDto::new).toList();
  }
  public void write(TodoWriteDto dto) {
    repository.save(dto.toEntity());
  }
  // 등록

  // 삭제
  
}
