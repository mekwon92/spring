package com.me92100984.member_post.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
	private Long rno;
	private String content;
	private Date regdate;
	private Date updatedate;
	private boolean hidden;
	private int likes;
	private String writer;
	private Long pno;
	
	

}
