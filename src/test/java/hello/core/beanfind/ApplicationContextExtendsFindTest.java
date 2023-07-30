package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextExtendsFindTest {
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

  @DisplayName("부모 타입을 조회 시, 자식이 둘 이상이면 중복 오류가 발생한다.")
  @Test
  void findBeanByParentTypeDuplicate() {
    // given
//    DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
    // when // then
    Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
        () -> ac.getBean(DiscountPolicy.class));
  }

  @DisplayName("부모 타입을 조회 시, 자식이 둘 이상이면 빈 이름을 지정한다.")
  @Test
  void findBeanByParentTypeBeanName() {
    // given
    DiscountPolicy bean = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
    // when // then
    assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
  }

  @DisplayName("자식 타입을 조회한다.")
  @Test
  void findBeanBySubType() {
    // given
    DiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
    // when // then
    assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
  }

  @DisplayName("부모 타입으로 모든 빈을 조회한다.")
  @Test
  void findAllBeanByParentType() {
    // given
    Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
    // when // then
    assertThat(beansOfType.size()).isEqualTo(2);
    for (String key : beansOfType.keySet()) {
      System.out.println("key  = " + key + " value = " + beansOfType.get(key));
    }
  }

  @DisplayName("부모 타입으로 모든 빈을 조회한다.")
  @Test
  void findAllBeanByObjectType() {
    // given
    Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
    // when // then
    for (String key : beansOfType.keySet()) {
      System.out.println("key  = " + key + " value = " + beansOfType.get(key));
    }
  }

  @Configuration
  static class TestConfig {// 위 클래스 안에서만 이 클래스를 쓰겠다
    @Bean
    public DiscountPolicy rateDiscountPolicy() {
      return new RateDiscountPolicy();
    }
    @Bean
    public DiscountPolicy fixDiscountPolicy() {
      return new FixDiscountPolicy();
    }
  }
}
