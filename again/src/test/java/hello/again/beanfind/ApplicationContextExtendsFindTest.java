package hello.again.beanfind;

import hello.again.discount.DiscountPolicy;
import hello.again.discount.FixDiscountPolicy;
import hello.again.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextExtendsFindTest {
     AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

     @Test
     @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면, 중복 오류가 발생한다.")
     void findBeanByParentTypeDuplicate(){

     }


     @Configuration
    static class TestConfig {
         @Bean
         public DiscountPolicy rateDiscountPolicy(){
             return new RateDiscountPolicy();
         }

         @Bean
         public DiscountPolicy fixDiscountPolicy(){
             return new FixDiscountPolicy();
         }

     }

}
