package hello.core.xml;

import hello.core.member.MemberService;
import org.assertj.core.api.AssertionErrorCollector;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlApplicationContext {
    ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    public void xmlAppContext(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
