package com.me92100984.todo.dto;

import com.me92100984.todo.domain.TodoEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TodoWriteDto {
  private String task;

  public TodoWriteDto(TodoEntity entity) {
    task = entity.getTask();
  }

  // dto >> entity
  public TodoEntity toEntity() {
    return TodoEntity.builder().task(task).build();
  }
}
