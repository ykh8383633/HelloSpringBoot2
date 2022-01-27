package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImple;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;

public class OrderApp {
    public static void main(String[] args){
        //MemberService memberService = new MemberServiceImple();
        //OrderService orderService = new OrderServiceImpl();

        // 컨피그 파일 사용
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();
        //OrderService orderService = appConfig.orderService();
        ApplicationContext applicationContext = new AnnotationConfigReactiveWebApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);


        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(member.getId(), "itemA", 5000);

        System.out.println("order: " + order);
        System.out.println("order calculate price: " + order.calculatePrice());
    }
}
