package com.me92100984.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.me92100984.todo.domain.TodoEntity;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
  @Modifying //수정,삭제는 @붙여야함..
  @Query("update todo t set t.done = true where t.id = :id")//jpql임. 별칭은 무조건 필요함.
  int updateTodoDoneById(@Param("id") Long id);

//메서드이름만 가지고 판단함..!
  List<TodoEntity> findByOrderByTaskDescIdAsc();

}
