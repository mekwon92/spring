package com.me92100984.mongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me92100984.mongo.entity.Student;
import com.me92100984.mongo.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class StudentService {

  @Autowired
  private final StudentRepository repository;
  
  //
  public void register(Student student) {
    repository.save(student);
  }

  //
  public List<Student> list() {
    return repository.findAll();

  }

  public Optional<Student> get(Long no) {
    return repository.findById(no);
  }

}
