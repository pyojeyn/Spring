package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1); //hello.core.member.MemberServiceImpl@2002fc1d
        System.out.println("memberService2 = " + memberService2); //hello.core.member.MemberServiceImpl@69453e37

        ////memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingleTonService singletonService1 = SingleTonService.getInstance();
        SingleTonService singletonService2 = SingleTonService.getInstance();

        // 같은 참조값을 가진 instance 반환
        System.out.println("singletonService1 = " + singletonService1); //SingleTonService@70325e14
        System.out.println("singletonService2 = " + singletonService2); //SingleTonService@70325e14

        assertThat(singletonService1).isSameAs(singletonService2);
        // same ==

    }
}
