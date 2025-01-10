package com.me92100984.club.controller;

import java.util.List;

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

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@Log4j2
@RestController
@RequestMapping("api/vi/notes")
public class NoteController {

  @Autowired
  private NoteService service;
  
  @PostMapping
  public Long post(@RequestBody NoteDTO dto) {
      return service.write(dto);
  }

  @GetMapping("list")
  public List<NoteDTO> list(String email) {
      return service.listByEmail(email);
  }

  @GetMapping("{num}")
  public NoteDTO get(@PathVariable Long num) {
    log.info(num);
    log.info(service.get(num));
    return service.get(num);
  }

  @PutMapping("{num}")
  public int modify(@PathVariable Long num, @RequestBody NoteDTO dto) {
      return service.modify(dto);
  }

  @DeleteMapping("{num}")
  public int remove(@PathVariable Long num) {
    return service.remove(num);
  }
  
  
  

  
  
  
}
