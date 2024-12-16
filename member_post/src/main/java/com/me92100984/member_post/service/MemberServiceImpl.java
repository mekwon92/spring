package com.me92100984.member_post.service;
//DAO, service, servlet은 mvc2패턴에서 필수적인....
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me92100984.member_post.mapper.MemberMapper;
import com.me92100984.member_post.mapper.PostMapper;
import com.me92100984.member_post.utils.MybatisInit;
import com.me92100984.member_post.vo.Member;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	//@Autowired
	private MemberMapper mapper;
	
	@Override
	public int register(Member member) {
		return mapper.insert(member);
	}

	@Override
	public Member findBy(String id) {
		return mapper.selectOne(id);
	}

	@Override
	public boolean login(String id, String pw) {
		Member m = findBy(id);
		return m != null && m.getPw().equals(pw);
	}

	@Override
	public List<Member> list() {
		return null;
	}

	@Override
	public boolean remove(String id) {
		return false;
	}

	@Override
	public boolean modify(Member member) {
		return false;
	}
}
