package hello.core.member;

public class MemberServiceImpl implements MemberService { // 배우1
  private final MemberRepository memberRepository; // 추상화

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
}
