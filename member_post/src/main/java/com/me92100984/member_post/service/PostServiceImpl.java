package com.me92100984.member_post.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.me92100984.member_post.dto.Criteria;
import com.me92100984.member_post.mapper.AttachMapper;
import com.me92100984.member_post.mapper.PostMapper;
import com.me92100984.member_post.mapper.ReplyMapper;
import com.me92100984.member_post.vo.Post;

import jakarta.servlet.ServletContextEvent;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@AllArgsConstructor
@Transactional
@Log4j2
public class PostServiceImpl implements PostService{
	private PostMapper mapper;
	private AttachMapper attachMapper;
	private ReplyMapper replyMapper;	


	@Override
	public int write(Post post) { 
		mapper.insert(post);
		log.error(post);

		post.getAttachs().forEach(a -> {
			a.setPno(post.getPno());
			log.info(a);
			attachMapper.insert(a);
		});
		return 0;
	}

	@Override
	public int modify(Post post) {
			return mapper.update(post);
	}
	
	@Override
	public int remove(Long pno) {
			attachMapper.delete(pno);
			replyMapper.deleteAll(pno);
			return mapper.delete(pno);
	}

	@Override
	public Post findBy(Long pno) {
			return mapper.selectOne(pno);
	}
	
	@Override
	public List<Post> list(Criteria cri){
		return mapper.selectList(cri);
	}
	
	@Override
	public Post view(Long pno) { 
		mapper.increaseViewCount(pno);	
		Post post = mapper.selectOne(pno);
		post.setAttachs(attachMapper.selectList(pno));
		return post;
	}

	@Override
	public int count(Criteria cri) {
		return mapper.getCount(cri);
	}
	
}
