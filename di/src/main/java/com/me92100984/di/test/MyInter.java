package com.me92100984.di.test;

//콜백함수 - 익명 함수로 정의된 함수 객체를 전달받아 실행, 파라미터로 함수전달
//람다식은 인터페이스 추상메서드가 1개


//함수형 인터페이스는 하나의 추상 메서드만 가지고 있는 인터페이스
@FunctionalInterface
public interface MyInter {
    Integer run(String str);

    default Integer de(String str) {
        return Integer.parseInt(str);
    }

    static Integer sm(String str) {
        return Integer.parseInt(str);
    }
}
