package hello.again;

import hello.again.discount.DiscountPolicy;
import hello.again.discount.FixDiscountPolicy;
import hello.again.member.MemberService;
import hello.again.member.MemberServiceImpl;
import hello.again.member.MemoryMemberRepository;
import hello.again.order.OrderService;
import hello.again.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(),new FixDiscountPolicy());
    }

    
}

/*
 AppConfig 는 실제 동작에 필요한 구현객체를 생성한다.
 AppConfig 는 생성한 객체 인스턴스의 참조(레퍼런스) 를 생성자를 통해서 주입 시켜준다.
* */