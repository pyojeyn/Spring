package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // JUnit 5 부터 public 지정 안해도됨.

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        // iter 치고 tap키 누르면 향상된 for문 자동 완성
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName); // Option + CMD + V 객체 =>for문 자동완성? Introduce Variable
            System.out.println("name = " + beanDefinitionName + " object = " + bean);
            
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        // iter 치고 tap키 누르면 향상된 for문 자동 완성
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);


            // Role ROLE_APPLICATION : 직접 등록한 애플리케이션 빈
            // Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION){  // 내가 등록한 빈들만 출력하기 위해서~
                Object bean = ac.getBean(beanDefinitionName); // Option + CMD + V 객체 자동완성? Introduce Variable
                System.out.println("name = " + beanDefinitionName + " object = " + bean);
            }

        }
    }
}
