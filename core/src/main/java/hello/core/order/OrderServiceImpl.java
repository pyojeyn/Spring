package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    // 인터페이스에만 의존 DIP 지킴
    // 무조건 세팅해줘. 필수!

    private  MemberRepository memberRepository;
    private  DiscountPolicy discountPolicy;

    /*
        수정자 주입 이거 하려면 위에 final빼야됨.
        setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 주입하는 방법이다.
        ↓특징
        선택, 변경 가능성이 있는 의존관계에 사용
        자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.
    * */
    @Autowired(required = false) // 선택 : 있어도 되고 없어도 될때
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }



    //생성자 주입
    /*
     특징 : 생성자 호출시점에 딱 1번만 호출되는 것이 보장된다,
           불변, 필수 의존관계에 사용
     중요! 생성자가 딱 1개만 있으면 @Autowired 를 생략해도 자동 주입된다
    * */
    @Autowired
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


    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}
