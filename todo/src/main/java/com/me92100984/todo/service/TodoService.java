package com.me92100984.todo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.me92100984.todo.domain.TodoEntity;
import com.me92100984.todo.dto.TodoListDto;
import com.me92100984.todo.dto.TodoWriteDto;
import com.me92100984.todo.repository.TodoRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class TodoService {
  
  private TodoRepository repository;
  private EntityManager manager; // modify2

  @PostConstruct
  public void init() {
    repository.saveAll(
      Stream.of(
        TodoEntity.builder().task("첫일").build(),
        TodoEntity.builder().task("둘일").build(),
        TodoEntity.builder().task("셋일").build())
        .toList()
    );
  }

  //필드 final 뺌.. 초기화블럭이용
  // {
  //   repository.saveAll(
  //     Stream.of(
  //       TodoEntity.builder().task("첫일").build(),
  //       TodoEntity.builder().task("둘일").build(),
  //       TodoEntity.builder().task("셋일").build())
  //       .toList()
  //   );
  // }

  // @AllArgsConstructor 뺌
  // public TodoService(TodoRepository repository) {
  //   this.repository = repository;
  //   repository.saveAll(
  //     Stream.of(
  //       TodoEntity.builder().task("첫일").build(),
  //       TodoEntity.builder().task("둘일").build(),
  //       TodoEntity.builder().task("셋일").build())
  //       .toList()
  //   );
  // }




  // 목록조회
  public List<TodoListDto> list() {
    // return repository.findAll().stream().map(TodoListDto::new).toList();
    // return repository.findByOrderByTaskDescIdAsc().stream().map(TodoListDto::new).toList();
    // return repository.findAll(Sort.by(Direction.DESC,"id")).stream().map(TodoListDto::new).toList(); // Sort로 정렬하는게 가장 견고함
    // return repository.findAll(Sort.by(Direction.DESC, "task","id")).stream().map(TodoListDto::new).toList(); //desc 여러개
    return repository.findAll(Sort.by(Order.desc("task"), Order.asc("id"))).stream().map(TodoListDto::new).toList(); //task desc, id asc
  }

  // 등록
  public void write(TodoWriteDto dto) {
    repository.save(dto.toEntity());
  }
  // 삭제
  public void remove(Long id) {
    //repository.delete(TodoEntity.builder().id(id).build());
    repository.deleteById(id);
  }
  // 수정.. jpa영속화
  @Transactional
  public void modify(Long id) {
    // 값을 가져와서 수정 -  @setter은 비추천임. 원래는 final해야하는거니까.....
    // Optional<TodoEntity> entity =  repository.findById(id);
    // entity.ifPresent(e->{ e.setDone(true); repository.save(e); });
    
    //task에 값을 직접 입력
    //repository.save(TodoEntity.builder().id(id).done(true).task("직접준것").build());

    repository.updateTodoDoneById(id);//jpql로 해보기

    
    
  }
  
  // Using EntityManager
  @Transactional
  public void modify2(Long id) {
    //Optional.of(manager.find(TodoEntity.class, id)).ifPresent(e-> e.setDone(true));
    manager.find(TodoEntity.class, id).setDone(true);
  }
}
