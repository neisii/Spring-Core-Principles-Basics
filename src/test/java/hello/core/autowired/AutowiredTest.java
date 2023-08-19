package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

  @DisplayName("")
  @Test
  void autowiredOption() {

    // given
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    // when

    // then

  }

  static class TestBean {
    @Autowired(required = false)
    public void setNoBean1(Member noBean1) { // 자동 주입 대상이 없으면(Spring Bean 이 아니면) 호출 자체가 안된다.
      System.out.println("TestBean.setNoBean1 " + noBean1);
    }
    @Autowired
    public void setNoBean2(@Nullable Member noBean2) { // 자동 주입 대상이 없으면 null
      System.out.println("TestBean.setNoBean2 " + noBean2);
    }
    @Autowired
    public void setNoBean3(Optional<Member> noBean3) { // Optional로 감싼다.
      System.out.println("TestBean.setNoBean3"  + noBean3);
    }
  }

}
