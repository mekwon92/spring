package com.me92100984.demo.vo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class MemberTests {
    Member m1 = new Member("abcd","1234","새똥이");
    Member m2 = new Member("abcd","1234","새똥이");

    // private Member member = Member.builder().build();

    // @Test
    // public void testMember() {
    //     //test 3단계. null인가?
    //     // given(주어진)
    //     Member m1 = Member.builder().id("abcd").pw("1234").name("새똥이").build();
    //     Member m2 = Member.builder().id("abcd").pw("1234").name("새똥이").build();

    //     // when ~ then(평가)
    //     log.info(String.format("%s@%X", m1.getClass().getName(), m1.hashCode()));
    //     log.info(String.format("%s@%X", m2.getClass().getName(), m2.hashCode()));

    //     // expect(기대값) / assert(검증)
    //     //assertNotNull(member);
    //     //assertNull(member);
    //     //assertNull(member.getId());
    //     assertSame(m1, m2);
    //     assertEquals(m1, m2);
    //     //Same은 주소값도 완벽히 일치하는지 확인
    //     //Equals는 
    // }

    // @Autowired
    // @Qualifier("member")
    // private Member member;
    // @Test
    // public void testDI() {
    //     System.out.println(member);
    // }
}


