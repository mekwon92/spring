package com.me92100984.member_post.vo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Attach {
	private String uuid;
	private String origin;
	private String path;
	private boolean image;
	private Long pno;
	
//	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException {
//		//java object to json
//		
//		List<Attach> attachs = new ArrayList<Attach>();
//		attachs.add(Attach.builder().uuid("abcd").origin("tmp.txt").build());
//		attachs.add(Attach.builder().uuid("efds").origin("tmp.png").build());
//		System.out.println(attachs);
//		
//		String s = new ObjectMapper().writeValueAsString(attachs);
//		System.out.println(s);
//	
//	}
}
