package com.me92100984.member_post.service;
//DAO, service, servlet은 mvc2패턴에서 필수적인....
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.me92100984.member_post.dao.MemberDao;
import com.me92100984.member_post.mapper.MemberMapper;
import com.me92100984.member_post.mapper.PostMapper;
import com.me92100984.member_post.utils.MybatisInit;
import com.me92100984.member_post.vo.Member;

public class MemberServiceImpl implements MemberService{
//	private MemberDao memberDao = MemberDao.getInstance(); //하는일이 같을 때는 싱글턴하는게 좋음 - 마이바티스 필요없
	
	@Override
	public int register(Member member) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){//true 한묶음의 트랜젝션으로 처리하겠다.. 아니면 commit 필요
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			return mapper.insert(member);
		}
	}

	@Override
	public Member findBy(String id) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			MemberMapper mapper = session.getMapper(MemberMapper.class);
			return mapper.selectOne(id);
		}
	}

	@Override
	public boolean login(String id, String pw) {
		Member m = findBy(id);
		return m != null && m.getPw().equals(pw);
	}

	@Override
	public List<Member> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modify(Member member) {
		// TODO Auto-generated method stub
		return false;
	}
}
