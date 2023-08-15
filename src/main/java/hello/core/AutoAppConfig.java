package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
    basePackages = "hello.core",
    basePackageClasses = AutoAppConfig.class,
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // AppConfig 제외하기 위함
)
public class AutoAppConfig {

  // 똑같은 이름으로 빈을 중복 선언 하면? 수동 등록 빈이 자동 등록 빈을 오버라이딩 한다.
  // 부트 서버 기동 시 기본 값은 오버라이딩 하지 않고 "오류 발생 시킴"
  // The bean 'memoryMemberRepository', defined in class path resource [hello/core/AutoAppConfig.class], could not be registered. A bean with that name has already been defined in file [out\production\classes\hello\core\member\MemoryMemberRepository.class] and overriding is disabled.
  @Bean(name = "memoryMemberRepository")
  MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }

}
