package hello.again.singleton;

import hello.again.AppConfig;
import hello.again.member.Member;
import hello.again.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleTonTest {


    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 : 호출할 때 마다 객체를 생성
         MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1  = "+memberService1);
        System.out.println("memberService2  = "+memberService2);

        /*
            결과
            memberService1  = hello.again.member.MemberServiceImpl@2002fc1d
            memberService2  = hello.again.member.MemberServiceImpl@69453e37
            다른 객체 생성
        */

        assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        /*
        결과 : 같은 인스턴스
        singletonService1 = hello.again.singleton.SingletonService@70325e14
        singletonService2 = hello.again.singleton.SingletonService@70325e14
        */

        assertThat(singletonService1).isSameAs(singletonService2);
        // 인스턴스를 비교할때는 isSameAs 나 isNotSameAs 를 비교
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱클톤")
    void springContainer(){
        //AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);


        System.out.println("memberService1  = "+memberService1);
        System.out.println("memberService2  = "+memberService2);

        /*
            결과
            memberService1  = hello.again.member.MemberServiceImpl@61c9c3fd
            memberService2  = hello.again.member.MemberServiceImpl@61c9c3fd
            같은 객체 생성
        */

        assertThat(memberService1).isSameAs(memberService2);

    }
}
