package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
  
  @DisplayName("")
  @Test
  void configurationTest() {
      
    // given
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
    OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
    MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

    // when
    MemberRepository memberRepository1 = memberService.getMemberRepository();
    MemberRepository memberRepository2 = orderService.getMemberRepository();
    MemberRepository memberRepository3 = memberRepository;

    // 모두 동일한 객체를 참조한다.
    System.out.println("memberService -> memberRepository" + memberRepository1);
    System.out.println("orderService -> memberRepository" +memberRepository2);
    System.out.println("memberRepository -> memberRepository" + memberRepository3);

    // then
    Assertions.assertThat(memberRepository1)
        .isSameAs(memberRepository2)
        .isSameAs(memberRepository3);
  }

  @DisplayName("")
  @Test
  void configurationDeep() {

    // given
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // when
    AppConfig bean = ac.getBean(AppConfig.class);
    System.out.println("bean = " + bean.getClass());

    // then

  }
}
