package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

public class ApplicationContextSameBeanFindTest {
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

  @DisplayName("같은 타입이 둘 이상 있으면 중복 오류가 발생한다.")
  @Test
  void findBeanByTypeDuplicate() {
    // given // when // then
    Assertions.assertThrows(NoUniqueBeanDefinitionException.class
        , () -> ac.getBean(MemberRepository.class));
  }

  @DisplayName("같은 타입이 둘 이상 있으면 빈 이름을 지정한다.")
  @Test
  void findBeanByByName() {
    // given // when // then
    MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
    org.assertj.core.api.Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
  }

  @DisplayName("특정 타입을 모두 조회하기")
  @Test
  void findAllBeanByType() {

    // given
    Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);

    // when
    for (String key : beansOfType.keySet()) {
      System.out.println("key = " + key + " value =" +beansOfType.get(key));
    }

    // then
    System.out.println("beansOfType = " + beansOfType);
    org.assertj.core.api.Assertions.assertThat(beansOfType.size()).isEqualTo(2);

  }

  @Configuration
  static class SameBeanConfig {// 위 클래스 안에서만 이 클래스를 쓰겠다
    @Bean
    public MemberRepository memberRepository1() {
      return new MemoryMemberRepository();
    }
    @Bean
    public MemberRepository memberRepository2() {
      return new MemoryMemberRepository();
    }
  }
}
