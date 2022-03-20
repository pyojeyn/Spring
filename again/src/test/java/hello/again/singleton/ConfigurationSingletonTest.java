package hello.again.singleton;

import hello.again.AppConfig;
import hello.again.member.MemberRepository;
import hello.again.member.MemberServiceImpl;
import hello.again.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();


        /*
          new 한번만 됨 같은 instance 조회.
        * MemberService -> memberRepository1 = hello.again.member.MemoryMemberRepository@1b2c4efb
          OrderService -> memberRepository2 = hello.again.member.MemoryMemberRepository@1b2c4efb
          memoryRepository = hello.again.member.MemoryMemberRepository@1b2c4efb
          같은 결과값
        * */
        System.out.println("MemberService -> memberRepository1 = " + memberRepository1);
        System.out.println("OrderService -> memberRepository2 = " + memberRepository2);
        System.out.println("memoryRepository = " + memberRepository);


        assertThat(memberRepository1).isSameAs(memberRepository);
        assertThat(memberRepository2).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean.getClass() = " + bean.getClass());
        //bean.getClass() = class hello.again.AppConfig$$EnhancerBySpringCGLIB$$ac4ed1dd

    }
}
