package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService { // 배우1
  private final MemberRepository memberRepository; // 추상화

  @Autowired
  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }
  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }

  public MemberRepository getMemberRepository() {
    return memberRepository;
  }

}
