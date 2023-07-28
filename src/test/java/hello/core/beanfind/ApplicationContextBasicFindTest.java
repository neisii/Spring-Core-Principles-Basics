package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {
  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @DisplayName("빈 이름으로 조회한다.")
  @Test
  void findBeanByName() {
    // given
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    // when // then
    System.out.println("memberService = " + memberService);
    System.out.println("memberService = " + memberService.getClass());
    assertThat(memberService).isInstanceOf(MemberService.class);
  }

  @DisplayName("이름 없이 타입으로만 조회")
  @Test
  void findBeanByType() {
    // given
    MemberService memberService = ac.getBean(MemberService.class); // 같은 타입이 여러 개일 경우 사용 어려움
    // when // then
    assertThat(memberService).isInstanceOf(MemberService.class);
  }

  @DisplayName("구체  타입으로 조회")
  @Test
  void findBeanByName2() {
    // given
    MemberService memberService = ac.getBean(MemberServiceImpl.class); // 같은 타입이 여러 개일 경우 유연성이 떨어진다.
    // when // then
    assertThat(memberService).isInstanceOf(MemberService.class);
  }

  @DisplayName("빈 이름으로 조회되지 않는다.")
  @Test
  void findBeanByNameX() {
    // given // when // then
    assertThrows(NoSuchBeanDefinitionException.class
        , () -> ac.getBean("XXXXX", MemberService.class));
  }

}
