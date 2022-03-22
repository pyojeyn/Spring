package hello.again.order;

import hello.again.annotation.MainDiscountPolicy;
import hello.again.discount.DiscountPolicy;
import hello.again.discount.FixDiscountPolicy;
import hello.again.discount.RateDiscountPolicy;
import hello.again.member.Member;
import hello.again.member.MemberRepository;
import hello.again.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    // 인터페이스에만 의존 => DIP 준수
    // final : 생성자 호출될때만 딱 한번
     private final MemberRepository memberRepository;
     private final DiscountPolicy discountPolicy;


    // new OrderServiceImpl(memberRepository, discountPolicy)
    // 생성자 1개일때 자동으로 Autowired 주입됨.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 수정자 주입
    // 수정자 주입은 @Autowired 해줘야 함.
    // '@Autowired' 의 기본 동작은 주입할 대상이 없으면 오류가 발생한다. 주입할 대사이 없어도 동작하게 하려면 '@Autowired(required = false)' 로 지정한다.
    // -> memberRepository 가 스프링 빈에 등록이 안되어있어도 됨.
//    @Autowired(required = false) // 있어도 되고 없어도 됨.
//    public void setMemberRepository(MemberRepository memberRepository){
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy){
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }




    // 인터페이스에만 의존하도록 설계와 코드 변경 ! 그럼 구현체는 어떻할까? 지금 이상태로 orderServiceTest 돌리면 NullPointException 발생
    // 이 문제를 해결하려면 누군가가 클라이언트인 OrderServiceImpl 에 DiscountPolicy 의 구현객체를 대신 생성하고 주입해주어야 함.



//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    => 잘 보면 클라이언트인 'OrderServiceImpl' 이 'DiscountPolicy' 인터페이스 뿐만 아니라 'FixDiscountPolicy' 인 구체클래스도 함께 의존하고 있음
//    => DIP 위반. 그래서 FixDiscountPolicy 를 RateDiscountPolicy 로 변경하는 순간 OrderServiceImpl 의 소스코드도 함께 변경해야한다. => OCP 위반 !


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인은 니가 알아서 단일책임 원칙 !
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
