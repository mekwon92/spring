package com.me92100984.club.service;

import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import com.me92100984.club.dto.AttachDTO;
import com.me92100984.club.dto.NoteDTO;
import com.me92100984.club.entity.Attach;
import com.me92100984.club.entity.Member;
import com.me92100984.club.entity.Note;

public interface NoteService {
  
  Long write(NoteDTO dto);
  Optional<NoteDTO> get(Long num);
  List<NoteDTO> listByEmail(String email);
  List<NoteDTO> listByMno(Long mno);
  List<NoteDTO> listAll();
  int modify(NoteDTO dto);
  int remove(Long num);


  default Note toEntity(NoteDTO dto) {
    Note note = Note.builder()
      .num(dto.getNum())
      .title(dto.getTitle())
      .content(dto.getContent())
      .member(Member.builder().email(dto.getWriter()).mno(dto.getMno()).build())
      .build();

      note.setAttachs(dto.getAttachDTOs().stream().map(a -> Attach.builder()
        .uuid(a.getUuid())
        .origin(a.getOrigin())
        .ext(a.getExt())
        .fileName(a.getFileName())
        .image(a.isImage())
        .mime(a.getMime())
        .path(a.getPath())
        .size(a.getSize())
        .url(a.getUrl())
        .note(note)
        .build()).toList()
      );
      return note;
  }

  default NoteDTO toDTO(Note note){
    return NoteDTO.builder()
      .num(note.getNum())
      .title(note.getTitle())
      .content(note.getContent())
      .writer(note.getMember().getEmail())
      .mno(note.getMember().getMno())
      .regDate(note.getRegDate())
      .modDate(note.getModDate())
      .attachDTOs(note.getAttachs().stream().map(a -> AttachDTO.builder()
        .uuid(a.getUuid())
        .ext(a.getExt())
        .origin(a.getOrigin())
        .fileName(a.getFileName())
        .image(a.isImage())
        .mime(a.getMime())
        .path(a.getPath())
        .size(a.getSize())
        .url(a.getUrl())
        .build()).toList()
       ).build();
  }
}
