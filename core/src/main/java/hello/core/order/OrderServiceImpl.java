package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    // 인터페이스에만 의존 DIP 지킴
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    //생성자 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) { //FIX 가 들어올지, RATE가 들어올지 모할
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();  //할인 정책 변경 이 코드는 DIP 위반이다. 이 클래스에서도 코드 변경을 해줘야 해서 결국 구현클래스에도 의존하고 있기 때문에 DIP위반!!/OCP위반
      //private DiscountPolicy discountPolicy; // 인터페이스에만 의존
    /*
    * DIP를 잘 지키려면 인터페이스에만 의존하도록 만들어야함
    * */

    // 단일책임의 원칙 잘 지켜짐 !할인데 관한 업무는 discountPolicy가 해결해줌
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인 정책에 회원을 넘겨버림

       return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
