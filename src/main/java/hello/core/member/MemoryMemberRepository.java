package hello.core.member;

import java.util.HashMap;
import java.util.Map;

/**
 * 회원 저장소가 확정되지 않았기 때문에 Memory에 구현한다.
 */
public class  MemoryMemberRepository  implements MemberRepository {

  private static Map<Long, Member> store = new HashMap<>(); // 직전 강의에서도 언급했음 ConcurrentHashMap
  @Override
  public void save(Member member) {
    store.put(member.getId(), member);
  }

  @Override
  public Member findById(Long memberId) {
    return store.get(memberId);
  }
}
