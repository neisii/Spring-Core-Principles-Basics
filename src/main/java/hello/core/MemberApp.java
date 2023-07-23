package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
  public static void main(String[] args) {
//    AppConfig appConfig = new AppConfig();
//    MemberService memberService = appConfig.memberService(); // == memberServiceImpl

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // Spring으로 전환하기 위함
    MemberService memberService = applicationContext.getBean("memberService", MemberService.class);// 빈 이름, 객체 타입

    Member member = new Member(1L, "memberA", Grade.VIP);
    memberService.join(member);

    Member findMember = memberService.findMember(member.getId());
    System.out.println("new member = " + member.getName());
    System.out.println("find member = " + findMember.getName());
  }
}
