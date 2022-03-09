package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); //스프링 빈 등록

    }

    static class TestBean {
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){ // Member는 스프링이 관리 하는 Bean이 아니다! 그냥 java 객체임
            System.out.println("noBean1" + noBean1);
        }
    }
}
