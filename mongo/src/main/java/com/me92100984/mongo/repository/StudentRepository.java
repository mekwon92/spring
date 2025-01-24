package com.me92100984.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.me92100984.mongo.entity.Student;

public interface StudentRepository extends MongoRepository<Student, Long> {
}
