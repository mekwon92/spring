package com.me92100984.member_post.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("member")
public class Member {
	private String id;
	private String pw;
	private String name;
	private String email;
	private String roadAddr;
	private String detailAddr;
	private Date regdate;//long타입을 쓰기도 함
	
	public Member() {}
	
	//생성자를 private로 만듬!(빌더패턴을 위해서)
	private Member(String id, String pw, String name, String email, String roadAddr, String detailAddr, Date regdate) {
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.email = email;
		this.roadAddr = roadAddr;
		this.detailAddr = detailAddr;
		this.regdate = regdate;
	}
	
	public int getNum() {
		return 10;
	}
	
	public static M builder() {
	//클래스 로드가 빨라서 인스턴스는 여기에 접근못함
		return new M();
	} 
	
	//빌더를 사용하기 위해 내부 클래스를 생성한다
	public static class M {
		String id;
		String pw;
		String name;
		String email;
		String roadAddr;
		String detailAddr;
		Date regdate;
		
		public M id(String id) {
			this.id = id;
			return this;
		}
		public M pw(String pw) {
			this.pw = pw;
			return this;
		}
		public M name(String name) {
			this.name = name;
			return this;
		}
		public M email(String email) {
			this.email = email;
			return this;
		}
		public M roadAddr(String roadAddr) {
			this.roadAddr = roadAddr;
			return this;
		}
		public M detailAddr(String detailAddr) {
			this.detailAddr = detailAddr;
			return this;
		}
		public M regdate(Date regdate) {
			this.regdate = regdate;
			return this;
		}
		
		
		public Member build() {
			return new Member(id, pw, name, email, roadAddr, detailAddr, regdate);
		}

		
	}
}
