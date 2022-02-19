package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 내 애플리케이션 전체를 설정하고 구성한다.
// AppConfig 는 애플리케이션의 실제 동작에 필요한 구현 객체를 생성 한다.
/* AppConfig 는 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결) 해준다.
   MemberServiceImpl -> MemoryMemberRepository
   OrderServiceImpl -> MemoryMemberRepository, FixDiscountPolicy */

// 설정 정보, 구성 정보
@Configuration
public class AppConfig {
    // 각 메서드에다 @Bean 을 작성 해준다. -> 스프링 컨테이너에 등록 됨.
    @Bean
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 할인 정책 바꾸고 싶을 떈 여기서만 바꿔주면 된다. Fix로 할건지 Rate로 할건지~
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
/*
* 객체의 생성과 연결은 AppConfig 가 담당한다.
* DIP 완성 : MemberServiceImpl 은 MemberRepository 인 추상에만 의존하면 된다. 이제 구체 클래스를 몰라도 된다.
* 관심사의 분리 : 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.
* */

}
