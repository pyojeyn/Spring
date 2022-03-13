package hello.again.order;

import hello.again.discount.DiscountPolicy;
import hello.again.discount.FixDiscountPolicy;
import hello.again.discount.RateDiscountPolicy;
import hello.again.member.Member;
import hello.again.member.MemberRepository;
import hello.again.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    // 인터페이스에만 의존 DIP 준수
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    // 인터페이스에만 의존하도록 설계와 코드 변경 ! 그럼 구현체는 어떻할까? 지금 이상태로 orderServiceTest 돌리면 NullPointException 발생
    // 이 문제를 해결하려면 누군가가 클라이언트인 OrderServiceImpl 에 DiscountPolicy 의 구현객체를 대신 생성하고 주입해주어야 함.




//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // => 잘 보면 클라이언트인 'OrderServiceImpl' 이 'DiscountPolicy' 인터페이스 뿐만 아니라 'FixDiscountPolicy' 인 구체클래스도 함께 의존하고 있음
    // => DIP 위반. 그래서 FixDiscountPolicy를 RateDiscountPolicy로 변경하는 순간 OrderServiceImpl의 소스코드도 함께 변경해야한다. => OCP 위반 !


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인은 니가 알아서 단일책임 원칙 !
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
