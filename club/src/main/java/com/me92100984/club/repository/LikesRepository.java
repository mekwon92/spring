package com.me92100984.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me92100984.club.entity.Likes;
import com.me92100984.club.entity.composite.LikesId;

public interface LikesRepository extends JpaRepository<Likes, LikesId>{
  
}
