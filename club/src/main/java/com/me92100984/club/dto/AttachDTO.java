package com.me92100984.club.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttachDTO {
  private String uuid;
  private String origin;
  private boolean image;
  private String path;

  private long size;
  private String mime;
  private String fileName;
  private String ext;
  private String url;

  private Long num;
  
}
