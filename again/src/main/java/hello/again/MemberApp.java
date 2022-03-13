package hello.again;

import hello.again.member.Grade;
import hello.again.member.Member;
import hello.again.member.MemberService;
import hello.again.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        // 스프링의 시작 ↓                            어노테이션 기반으로 Configure 하는 class 파라미터로 넘겨주기.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);// 첫번째 파라미터 : 이름 / 두번째 파라미터 : 타입



//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "제니", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + member);
        System.out.println("find Member = " + findMember.getName());


    }
}
