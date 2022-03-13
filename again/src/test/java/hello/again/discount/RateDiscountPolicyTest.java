package hello.again.discount;

import hello.again.member.Grade;
import hello.again.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10%할인이 적용되어야 한다.")
    void vip_o(){
        //given
        Member 후니 = new Member(1L, "후니", Grade.VIP);

        //when
        int discount = discountPolicy.discount(후니,10000);

        //then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP 가 아니면 할인이 적용되지 않아야 한다.")
    void vip_X(){
        //given
        Member 제니 = new Member(2L, "제인", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(제니,10000);

        //then
        assertThat(discount).isEqualTo(0); // => option 엔터 치고 static import 해주자.
    }
}