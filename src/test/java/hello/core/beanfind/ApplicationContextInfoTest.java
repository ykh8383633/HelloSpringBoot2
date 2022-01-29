package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    public void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach((name) -> {
            Object bean = ac.getBean(name);
            System.out.println("name: " + name + " object: " + bean);
        });
    }

    @Test
    @DisplayName("어플리케이션 빈 출력하기")
    public void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).filter((name) -> {
            BeanDefinition beanDefinition = ac.getBeanDefinition(name);
            // ROLE_APPLICATION: 직접등록한 빈
            // ROLE_INFRASTRUCTURE: 스프링 내부에서 사용하느 빈
            return beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION; // 스프링 기본 빈 말고 내가 설정한 빈만을 남긴다.
        }).forEach((name) -> {
            Object bean = ac.getBean(name);
            System.out.println("name: " + name + " object: " + bean);
        });
    }
}
