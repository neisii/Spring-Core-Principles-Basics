package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService { // 배우2
  private final MemberRepository memberRepository; // 인터페이스에만 의존하도록 변경
  private final DiscountPolicy discountPolicy; // 인터페이스에만 의존하도록 변경

  @Autowired // 생성자 호출 시점에 딱 1번만 호출되는 것이 보장된다. (불변, 필수 의존 관계에 사용)
  // 생성자가 단 1개만 존재하는 경우 Autowired 생략 가능
// @RequiredArgsConstructor: final 붙은 필드로 생성자를 만든다.
  public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy DiscountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = DiscountPolicy;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);

    return new Order(memberId, itemName, itemPrice, discountPrice);
  }

  // 테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }

}
