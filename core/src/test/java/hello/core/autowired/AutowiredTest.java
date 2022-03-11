package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); //스프링 빈 등록
    }

    static class TestBean {
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){ // Member는 스프링이 관리 하는 Bean이 아니다! 그냥 java 객체임
            System.out.println("noBean1 = " + noBean1);
        }
        /*
            @Autowired(required=false) : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨 (아예 호출 자체가 안됨)
            Member 는 스프링 빈이 아니다.
        */

        @Autowired
        public void setNoBean2(@Nullable Member noBean1){ // Member는 스프링이 관리 하는 Bean이 아니다! 그냥 java 객체임
            System.out.println("noBean2 = " + noBean1);
        } // null 자동 주입할 대상이 없으면 null 이 입력된다.

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3); // Optional.empty
        } // 자동 주입할 대상이 없으면 Optional.empty 가 입력된다.


    }
}

/*
    출력결과
    noBean1은 출력안됨~
    noBean2 = null
    noBean3 = Optional.empty
*/