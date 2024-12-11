package com.me92100984.di.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.me92100984.di.vo.Post;

import lombok.extern.log4j.Log4j2;

@Log4j2
//@Service("notice") 이렇게 안하고 다른방법사용 - @Configuration(Bean을 쓰려면 이걸 적용해야 탐색 대상이 됨) @Bean
@Configuration
public class PostServiceImplNotice implements PostService{

    @Override
    public List<Post> list() {
        List<Post> list = new ArrayList<>();
        list.add(Post.builder().pno(5L).title("공지게시판제목").writer("작성자").build());
        list.add(Post.builder().pno(6L).title("공지게시판제목").writer("작성자").build());
        return list;
    }

    @Override
    public void write(Post post) {
        log.info(getClass().getSimpleName()+".write() call");        
    }
    
    //Bean은 여러가지 적용가능(destroyMethod을 통해 자원반납 등등)
    @Bean //얘도 이름 따로 지정 안하면 postService가 됨
    public PostService postService() {
        return new PostServiceImplNotice();
    }

    
}
