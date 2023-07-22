package hello.core.member;

import hello.core.AppConfig;
import hello.core.order.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {
  MemberService memberService;
  @BeforeEach
  void setUp() {
    AppConfig appConfig = new AppConfig();
    memberService = appConfig.memberService();
  }

  @DisplayName("회원을 등록한다.")
  @Test
  void join() {

    // given
    Member member = new Member(1L, "memberA", Grade.VIP);

    // when
    memberService.join(member);
    Member findMember = memberService.findMember(member.getId());

    // then
    assertThat(member).isEqualTo(findMember);
  }

}