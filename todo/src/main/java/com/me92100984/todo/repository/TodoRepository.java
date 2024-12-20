package com.me92100984.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me92100984.todo.domain.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long>{
    
}
