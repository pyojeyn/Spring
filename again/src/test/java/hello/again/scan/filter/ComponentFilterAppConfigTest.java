package hello.again.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class); // 'includeFilters' 에 'MyIncludeComponent' 애노테이션을 추가해서 BeanA가 스프링 빈에 등록된다.

        assertThat(beanA).isNotNull();

        //BeanB beanB = ac.getBean("beanB", BeanB.class); //No bean named 'beanB' available
        // excludeFilters 에 'MyExcludeComponent' 애노테이션을 추가해서 BeanB는 스프링 빈에 등록되지 않는다.
        assertThrows(
                NoSuchBeanDefinitionException.class,
                ()-> ac.getBean("beanB", BeanB.class)
        );

    }

    @Configuration
    @ComponentScan(includeFilters = @Filter(type = FilterType.ANNOTATION,classes = MyIncludeComponent.class),
    excludeFilters = @Filter(type = FilterType.ANNOTATION,classes = MyExcludeComponent.class))
    static class ComponentFilterAppConfig{

    }
}
