package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

  @DisplayName("")
  @Test
  void basicScan() {
    // given
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    // when
    MemberServiceImpl memberService = ac.getBean(MemberServiceImpl.class);
    // then
    Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
  }
}
