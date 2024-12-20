package com.me92100984.member_post.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Alias("post")
public class Post {
	private Long pno;
	private String title;
	private String writer;
	private String content;
	private Long viewCount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date regdate;
	private Date updatedate;
	private Integer cno;
	private Boolean attachFlag;
	
	private List<Attach> attachs = new ArrayList<>();

	// public Post(Long pno, String title, String writer, String content, Long viewCount, Date regdate, Date updatedate, Integer cno, Boolean attachFlag) {
	// 	super();
	// 	this.pno = pno;
	// 	this.title = title;
	// 	this.writer = writer;
	// 	this.content = content;
	// 	this.viewCount = viewCount;
	// 	this.regdate = regdate;
	// 	this.updatedate = updatedate;
	// 	this.cno = cno;
	// 	this.attachFlag = attachFlag;
	// }
}
