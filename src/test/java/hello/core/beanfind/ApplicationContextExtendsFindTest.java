package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextExtendsFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모타입 조회시, 자식이 둘이상이면 중복 오류 발생")
    public void FindBeanByParentDuplicate(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
        // 오류가 안나고 싶으면 이름과 타입 둘다 입력하면 됨
    }

    @Test
    @DisplayName("같은 부모를 상속받은 빈 모두 꺼내기")
    public void findAllByParent(){
        Map<String, DiscountPolicy> beans = ac.getBeansOfType(DiscountPolicy.class);
        beans.forEach((k ,v) -> {
            System.out.println("keys: " + k + " value: " + v);
        });

        System.out.println("beansOfType: " + beans);
        org.assertj.core.api.Assertions.assertThat(beans.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모타입으로 모두 조회하기")
    public void findAllByObject(){
        Map<String, Object> beans = ac.getBeansOfType(Object.class);
        beans.forEach((k ,v) -> {
            System.out.println("keys: " + k + " value: " + v);
        });

        System.out.println("beansOfType: " + beans);
    }

    @Configuration
    static class TestConfig {
        @Bean
        public FixDiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }
        @Bean
        public RateDiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }
    }
}
