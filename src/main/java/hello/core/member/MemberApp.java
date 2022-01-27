package hello.core.member;

import hello.core.AppConfig;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;

public class MemberApp {
    public static void main(String[] args){

        // 여기서 직접주입
        //MemberService memberService = new MemberServiceImple();
        // 컨피스 파일 사용
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();

        // 스프링 사용
        ApplicationContext applicationContext = new AnnotationConfigReactiveWebApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member: " + member.getName());
        System.out.println("find member: " + findMember.getName());
    }
}
