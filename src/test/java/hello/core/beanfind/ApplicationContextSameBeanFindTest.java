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

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면 오류 발생")
    public void FindBeanByTypeDuplicate(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class, () -> ac.getBean(DiscountPolicy.class));
        // 오류가 안나고 싶으면 이름과 타입 둘다 입력하면 됨
    }

    @Test
    @DisplayName("같은타입 모두 꺼내기")
    public void findAllByType(){
        Map<String, DiscountPolicy> beans = ac.getBeansOfType(DiscountPolicy.class);
        beans.forEach((k ,v) -> {
            System.out.println("keys: " + k + " value: " + v);
        });

        System.out.println("beansOfType: " + beans);
        org.assertj.core.api.Assertions.assertThat(beans.size()).isEqualTo(2);


    }


    @Configuration
    static class SameBeanConfig {
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
