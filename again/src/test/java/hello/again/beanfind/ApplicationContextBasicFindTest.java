package hello.again.beanfind;

import hello.again.AppConfig;
import hello.again.member.MemberService;
import hello.again.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService); // memberService = hello.again.member.MemberServiceImpl@3cc41abc
        System.out.println("memberService.getClass() = " + memberService.getClass()); // memberService.getClass() = class hello.again.member.MemberServiceImpl
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // Option 엔터 -> static import
    }



    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){ // ctrl + shift + R 해당 메소드 실행
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = " + memberService); // memberService = hello.again.member.MemberServiceImpl@7f6f61c8
        System.out.println("memberService.getClass() = " + memberService.getClass()); // memberService.getClass() = class hello.again.member.MemberServiceImpl
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // Option 엔터 -> static import
    }


    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        System.out.println("memberService = " + memberService); // memberService = hello.again.member.MemberServiceImpl@3cc41abc
        System.out.println("memberService.getClass() = " + memberService.getClass()); // memberService.getClass() = class hello.again.member.MemberServiceImpl
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // Option 엔터 -> static import
    }
    // 구체 타입으로 적는건 좋지 않다. => 항상 역할과 구현을 구분해야 하고 역할에만 의존해야하는데 이거는 구현에만 의존하는 코드이기 때문에 좋은 코드는 아니다.

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX(){
//        ac.getBean("xxxxx", MemberService.class); => NoSuchBeanDefinitionException
        assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean("xxxxx", MemberService.class));
        // 오른쪽에 있는 로직이 터지면 왼쪽에 있는 NoSuchBeanDefinitionException 이 터져야 한다.

    }
}
