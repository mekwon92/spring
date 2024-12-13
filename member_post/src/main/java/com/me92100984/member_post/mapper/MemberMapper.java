package com.me92100984.member_post.mapper;

import com.me92100984.member_post.vo.Member;

public interface MemberMapper {
	int insert(Member member);
	Member selectOne(String id);
}
