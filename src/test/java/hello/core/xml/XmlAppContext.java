package hello.core.xml;

import hello.core.member.MemberService;
import hello.core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class XmlAppContext {

  @DisplayName("")
  @Test
  void xmlAppContext() {
    ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
  }
  @DisplayName("")
  @Test
  void xmlAppContext2() {
    ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    OrderService orderService = ac.getBean("orderService", OrderService.class);
    Assertions.assertThat(orderService).isInstanceOf(OrderService.class);
  }
}
