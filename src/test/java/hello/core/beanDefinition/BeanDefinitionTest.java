package hello.core.beanDefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class BeanDefinitionTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    public void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).filter((name)->
                ac.getBeanDefinition(name).getRole() == BeanDefinition.ROLE_APPLICATION).
                forEach((name) -> {
            BeanDefinition beanDefinition = ac.getBeanDefinition(name);
            System.out.println("beanDefinitionName: " + name +
                    " beanDefinition: " + beanDefinition);
        });
    }
}
