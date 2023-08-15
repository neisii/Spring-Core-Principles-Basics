package hello.core.scan.filter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {

  @DisplayName("")
  @Test
  void filterScan() {

    // given
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
    // when
    BeanA beanA = ac.getBean("beanA", BeanA.class);

    // then
    assertThat(beanA).isNotNull();

    assertThrows(
        NoSuchBeanDefinitionException.class, () -> ac.getBean("beanB", BeanB.class)
    );
  }

  // 커스텀 애노테이션 활용
  @Configuration
  @ComponentScan(
      includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
      excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
  )
  static class ComponentFilterAppConfig {}
}
