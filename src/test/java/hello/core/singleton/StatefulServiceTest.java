package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

  @DisplayName("")
  @Test
  void statefulServiceSingleton() {

    // given
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulService statefulService1 = ac.getBean(StatefulService.class);
    StatefulService statefulService2 = ac.getBean(StatefulService.class);

    // when
    statefulService1.order("userA", 10000);
    statefulService2.order("userB", 20000);

    // ThreadA: 사용자 A 주문 금액 조회
    int price1 = statefulService1.getPrice();
    System.out.println(price1);

    // then
    // 주의! 싱글톤 객체이기 때문에 마지막 주문 금액이 나온다.
    Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
  }


  static class TestConfig {

    @Bean
    public StatefulService statefulService() {
      return new StatefulService();
    }
  }
}