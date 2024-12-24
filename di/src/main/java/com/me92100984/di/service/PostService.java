package com.me92100984.di.service;

import java.util.List;


import com.me92100984.di.vo.Post;

public interface PostService {
    // void write(Post post); 를 바꿔보자.. 
    default void write(Post post) {
    }
    List<Post> list();
}
