package com.me92100984.club.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me92100984.club.dto.NoteDTO;
import com.me92100984.club.service.NoteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/notes")
// @CrossOrigin
public class NoteController {

  @Autowired
  private NoteService service;
  
  @PostMapping
  public Long write(@RequestBody NoteDTO dto) {
    log.info(dto);
    return service.write(dto);
  }


  @GetMapping("list")
  public List<NoteDTO> list(String email) {
      return service.listByEmail(email);
  }

  @GetMapping("listall")
  public List<NoteDTO> listAll() {
      return service.listAll();
  }

  @SuppressWarnings("unchecked")
  @GetMapping("{num}")
  public ResponseEntity<?> get(@PathVariable Long num) {
    log.info(num);
    log.info(service.get(num));
    return service.get(num).map(ResponseEntity::ok)
      .orElseGet(() -> {
        Map<String, Object> ret = new HashMap<>();
        ret.put("code", 404);
        ret.put("message", "NOT_FOUND");
        ResponseEntity<?> entity = new ResponseEntity<>(ret, HttpStatus.NOT_FOUND);
        return (ResponseEntity<NoteDTO>) entity;
    }); 
    //원래는 전역으로 설정해야 함(orElseThrow로...), @RestControllerAdvice로 이용!(프로젝트)
    //각각의 스트림마다 무슨형태가 반환될것인지 생각해보세요...

  }

  @PutMapping("{num}")
  public String modify(@PathVariable Long num, @RequestBody NoteDTO dto) {
      return service.modify(dto) > 0 ? "success" : "failure" ;
  }

  @DeleteMapping("{num}")
  public String remove(@PathVariable Long num) {
    return service.remove(num) > 0 ? "success" : "failure" ;
  }
  
  
  

  
  
  
}
