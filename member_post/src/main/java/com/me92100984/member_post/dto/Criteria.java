package com.me92100984.member_post.dto;
// 페이지에 관한 규정, 표준
// 쿼리스트링

import java.lang.reflect.Field;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Criteria {
	//변할수 있게 (포스트 멤버는 final 해야됨)
	private int page = 1; // 현재 페이지 
	private int amount = 10; // 한 페이지당 보여줄 게시글 수
	private int category = 2;
	private String type;
	private String keyword;
	
	public int getOffset() { // 데이터베이스에서 몇 번째 데이터부터 가져올지를 계산
		return (page - 1) * 10;
	}
	
	//request 분석 후 필드초기화
	public Criteria (HttpServletRequest req) {
		if(req == null) return;
		
		Field[] fields = getClass().getDeclaredFields(); //reflect
		for(Field field : fields) {
			String tmp = req.getParameter(field.getName());
//			System.out.println(field);
//			System.out.print(field.getType() + "::");//type 알아오기
//			System.out.print((field.getType() == String.class) + "::");
//			System.out.println(field.getType() == int.class);
			if(tmp != null && !tmp.equals("")) {
				//invocate?
				try {
					Object obj = tmp;
					if(field.getType() == int.class) {
						obj = Integer.parseInt(tmp);
					}
					field.set(this, obj);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}	
	}
//	public static void main(String[] args) {
//		new Criteria(null);
//	}
	
	//query string 문자열 생성(el에서 사용될 예정)
	
	
	// page 포함
	public String getQs2() {
		return "page="+page+"&"+getQs();
	}
	
	// page 불포함
	public String getQs() {
		Field[] fields = getClass().getDeclaredFields();	
		String[] strs = new String[4];
		Stream.of(fields).filter(f->!f.getName().equals("page")).map(f-> {
			String r = null;
			try {
				r = f.getName()+ "=" +(f.get(this) == null ? "" : f.get(this)); 
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return r;
			
		}).collect(Collectors.toList()).toArray(strs);
		return String.join("&", strs);
	}
	
	public String[] getTypeArr() {
		return type.split("");
	}
	
	public static void main(String[] args) {
//		System.out.println(new Criteria().getQs());
	}
}
