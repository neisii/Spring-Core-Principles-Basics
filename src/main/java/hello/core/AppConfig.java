package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig { // 실제 동작에 필요한 *구현 객체를 생성한다. == 공연기획자

  @Bean
  public MemberService memberService() { // 생성자를 통해 주입(Injection)
    return new MemberServiceImpl(memberRepository());
  }

  @Bean
  public MemoryMemberRepository memberRepository() {
    return new MemoryMemberRepository(); // 저장소 선택
  }

  @Bean
  public OrderService orderService() {
    return new OrderServiceImpl(memberRepository(), discountPolicy());
  }

  @Bean
  public DiscountPolicy discountPolicy() {
//    return new FixDiscountPolicy(); // 적용할 할인 정책
    return new RateDiscountPolicy();
  }

}
