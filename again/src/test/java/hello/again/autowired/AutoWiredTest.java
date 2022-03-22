package hello.again.autowired;

import hello.again.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {
    @Test
    void AutoWiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        @Autowired(required = false)
        public void setNoBean1(Member noBean1){ // Member 는 스프링 빈이 아님
            System.out.println("noBean1 = " + noBean1); // 아예 호출 안됨.
        }

        @Autowired
        public void setNoBean2(@Nullable  Member noBean2){ // Member 는 스프링 빈이 아님
            System.out.println("noBean2 = " + noBean2); // noBean2 = null
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3); // noBean3 = Optional.empty
        }

    }
}
