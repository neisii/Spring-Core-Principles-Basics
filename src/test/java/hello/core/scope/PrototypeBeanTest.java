package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeBeanTest {

    @DisplayName("")
    @Test
    void prototypeBeanFind() {

        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        // when
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2  = " + prototypeBean2);

        // then
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close(); // 프로토타입 빈은 스프링 컨테이너가 종료될 때 종료 메서드가 전혀 실행되지 않음.
        prototypeBean1.destroy(); // 이렇게 빈을 호출한 클라이언트가 직접 종료 해야 한다.
        prototypeBean2.destroy();
    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");

        }
    }

}
