package com.me92100984.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me92100984.demo.mapper.MemberMapper;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MemberService {
    @Autowired
    private MemberMapper mapper;
    
    public String selectNow() {
        //log.info(mapper.selectNow());
        log.error(mapper.selectNow());
        return mapper.selectNow();
    }
}
