package com.me92100984.club.entity.composite;

import java.io.Serializable;

import com.me92100984.club.dto.LikesDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Getter
@Setter
public class LikesId implements Serializable {
  private Long member;
  private Long note;  

  public LikesId(LikesDTO dto) {
    this.member = dto.getMno();
    this.note = dto.getNum();
  }
}

