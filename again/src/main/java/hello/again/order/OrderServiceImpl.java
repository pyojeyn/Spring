package hello.again.order;

import hello.again.discount.DiscountPolicy;
import hello.again.discount.FixDiscountPolicy;
import hello.again.member.Member;
import hello.again.member.MemberRepository;
import hello.again.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인은 니가 알아서 단일책임 원칙 !
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
