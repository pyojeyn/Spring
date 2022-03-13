package hello.again;

import hello.again.discount.DiscountPolicy;
import hello.again.discount.FixDiscountPolicy;
import hello.again.discount.RateDiscountPolicy;
import hello.again.member.MemberRepository;
import hello.again.member.MemberService;
import hello.again.member.MemberServiceImpl;
import hello.again.member.MemoryMemberRepository;
import hello.again.order.OrderService;
import hello.again.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 설정정보, 구성정보
@Configuration
public class AppConfig {
//  각 메서드에 @Bean => 스프링 컨테이너에 등록 기본적으로 메서드 이름으로 등록됨.
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository() ,discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new RateDiscountPolicy();
        return new FixDiscountPolicy();
    }
    
}

/*
 AppConfig 는 실제 동작에 필요한 구현객체를 생성한다.
 AppConfig 는 생성한 객체 인스턴스의 참조(레퍼런스) 를 생성자를 통해서 주입 시켜준다.
* */