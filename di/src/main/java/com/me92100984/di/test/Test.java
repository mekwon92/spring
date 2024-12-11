package com.me92100984.di.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Test {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Stream.of(1,2,3,4,5).toList());
        log.info(list);

        Comparator<Integer> c = (a,b) ->  b - a;
        list.sort(c);
        log.info(list);
        
        //생성
        MyInter myInter = new MyInter() {
            //익명클래스
            public Integer run(String str) {
                return 10;
            }
        };
        
        log.info(myInter.de("100"));
        log.info(myInter.run(""));

        log.info(MyInter.sm("3O"));
        
        /* 익명함수 대체 람다식
        람다식은 자바에서 **함수를 일급 객체처럼 전달**하기 위해 사용! 
        Java는 함수 자체를 전달하는 개념을 직접 지원하지 않음. 대신, 함수형 인터페이스를 통해 람다식을 전달.
        따라서, a -> 50은 함수라기보다는 함수형 인터페이스의 메서드 구현체임*/
        MyInter myInter2 = a -> 50; //(a) -> {return 50;}
        log.info(myInter2.run(null));
    }
}
