package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;


import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

  @DisplayName("")
  @Test
  void prototypeFind() {
    // given
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

    // when
    System.out.println("Find prototypeBean1");
    PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
    prototypeBean1.addCount();

    // then
    assertThat(prototypeBean1.getCount()).isEqualTo(1);
    System.out.println("Find prototypeBean2");

    PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
    prototypeBean2.addCount();
    assertThat(prototypeBean2.getCount()).isEqualTo(1);
  }
  @DisplayName("")
  @Test
  void singletonClientUsePrototype() {
    // given
    AnnotationConfigApplicationContext ac =
        new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

    ClientBean clientBean1 = ac.getBean(ClientBean.class);
    int count1 = clientBean1.logic();
    assertThat(count1).isEqualTo(1);

    ClientBean clientBean2 = ac.getBean(ClientBean.class);
    int count2 = clientBean2.logic();
    assertThat(count2).isEqualTo(1);
  }

  @Scope("singleton")
  static class ClientBean {
//    private final PrototypeBean prototypeBean; // 생성 시점에 주입

    @Autowired
    private Provider<PrototypeBean> provider;

//    @Autowired
//    public ClientBean(PrototypeBean prototypeBean) {
//      this.prototypeBean = prototypeBean;
//    }

    public int logic() {
      PrototypeBean prototypeBean = provider.get();
      prototypeBean.addCount();
      int count = prototypeBean.getCount();
      return count;
    }
  }

  @Scope("prototype")
  static class PrototypeBean {
    private int count = 0;

    public int getCount() {
      return count;
    }

    public void setCount(int count) {
      this.count = count;
    }

    public int addCount() {
      return count++;
    }

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init");
    }

    @PreDestroy
    public void destory() {
      System.out.println("PrototypeBean.destory");
    }
  }
}
