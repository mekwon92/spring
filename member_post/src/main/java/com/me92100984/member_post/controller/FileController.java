package com.me92100984.member_post.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.me92100984.member_post.vo.Attach;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@Log4j2
public class FileController {
  //업로드
  @PostMapping("upload")
  public List<Attach> upload(@RequestPart("file") List<MultipartFile> files) throws Throwable,IOException{
    return files.stream().map(Attach::new).toList();
  }
  //다운로드
  
  //이미지 뷰

}