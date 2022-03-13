package hello.again;

import hello.again.member.Grade;
import hello.again.member.Member;
import hello.again.member.MemberService;
import hello.again.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {

        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
//        MemberService memberService = new MemberServiceImpl();

        Member member = new Member(1L, "제니", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("new member = " + member);
        System.out.println("find Member = " + findMember.getName());


    }
}
