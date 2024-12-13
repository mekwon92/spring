package com.me92100984.member_post.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.me92100984.member_post.dao.PostDao;
import com.me92100984.member_post.dto.Criteria;
import com.me92100984.member_post.mapper.AttachMapper;
import com.me92100984.member_post.mapper.PostMapper;
import com.me92100984.member_post.utils.MybatisInit;
import com.me92100984.member_post.vo.Attach;
import com.me92100984.member_post.vo.Post;

public class PostServiceImpl implements PostService{
//	private PostDao dao = new PostDao();
	
	public static void main(String[] args) {
		new PostServiceImpl().write(Post.builder().title("제목").content("abcd").writer("mekwon").cno(2).build());
	}
	
	@Override
	public int write(Post post) { //mb 자원관리때문에 메서드안에 메서드마다 넣어야함....ㅠ
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			PostMapper mapper = session.getMapper(PostMapper.class);
			//첨부파일....
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			System.out.println(post); // post.getPno() = null
			mapper.insert(post);
			System.out.println(post); // post.getPno() != null
			post.getAttachs().forEach(a -> {
				a.setPno(post.getPno());
				attachMapper.insert(a);
			});
			return 0;
		}
	}
	
	@Override
	public int modify(Post post) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			PostMapper mapper = session.getMapper(PostMapper.class);
			return mapper.update(post);
		}
	}
	
	@Override
	public int remove(Long pno) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			PostMapper mapper = session.getMapper(PostMapper.class);
			//첨부파일도
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			attachMapper.delete(pno);
			return mapper.delete(pno);
		}
	}

	@Override
	public Post findBy(Long pno) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			PostMapper mapper = session.getMapper(PostMapper.class);
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			Post post = mapper.selectOne(pno);
			post.setAttachs(attachMapper.selectList(pno));
			return post;
		}
	}
	
	@Override
	public Post view(Long pno) { //부하가 심한 코드임 -> 나중엔 테이블이 아니라 시퀀스 등으로 처리하거나 nosql로 ...처리?
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			PostMapper mapper = session.getMapper(PostMapper.class);
			AttachMapper attachMapper = session.getMapper(AttachMapper.class);
			mapper.increaseViewCount(pno);
			Post post = mapper.selectOne(pno);
			post.setAttachs(attachMapper.selectList(pno));
			return post;
		}
	}
	
	@Override
	public List<Post> list(Criteria cri){
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			PostMapper mapper = session.getMapper(PostMapper.class);
			return mapper.selectList(cri);
		}
	}

	@Override
	public int count(Criteria cri) {
		try(SqlSession session = MybatisInit.getInstance().sqlSessionFactory().openSession(true)){
			PostMapper mapper = session.getMapper(PostMapper.class);
			return mapper.getCount(cri);
		}
	}
	
}
