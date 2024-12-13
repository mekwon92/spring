package com.me92100984.member_post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.me92100984.member_post.dto.Criteria;
import com.me92100984.member_post.vo.Post;

public interface PostMapper {
//	List<Post> selectList();
//	
//	
//	@Select("select now()") //짧은 구문은 xml대신 여기서도 사용이 가능함
//	String now();
//	
//	int write(Post post);
	
	int insert(Post post);
	
	Post selectOne(Long pno);
	
	int getCount(Criteria cri);
	
	List<Post> selectList(Criteria cri);
	
	int update(Post post);
	
	int increaseViewCount(Long pno);
	
	int delete(Long pno);
	
}
