package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // 스프링은 모든게 ApplicationContext 부터 시작함. (스프링 컨테이너) Bean 들을 다 관리해주는거임.
        // AppConfig 에 있는 환경설정 정보를 가지고 스프링 컨테이너에 집어넣어서 관리해준다
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class); // AppConfig 의 memberService(), 옆에는 반환 타입.

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member); // 회원가입

        Member findMember = memberService.findMember(1L); // cmd + option + v
        System.out.println("new member = " + member.getName());
        System.out.println("member = " + findMember.getName());
    }
}
