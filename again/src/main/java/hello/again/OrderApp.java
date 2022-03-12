package hello.again;

import hello.again.member.Grade;
import hello.again.member.Member;
import hello.again.member.MemberService;
import hello.again.member.MemberServiceImpl;
import hello.again.order.Order;
import hello.again.order.OrderService;
import hello.again.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) { // 실행 단축키 ctrl + shift + R
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);
        Order diorBag = orderService.createOrder(memberId, "DiorBag", 3000000);
        System.out.println("diorBag = " + diorBag);
        System.out.println("계산된 가격 : " + diorBag.calculatePrice()); // 0311 여기까지 했음;

    }
}
