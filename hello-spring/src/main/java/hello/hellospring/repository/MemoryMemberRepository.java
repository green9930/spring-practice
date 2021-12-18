package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {

  private static Map<Long, Member> store = new HashMap<>();
  private static long sequence = 0L;

  @Override
  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Optional<Member> findByName(String name) {
    return store.values().stream()
      // 매개변수로 넘어온 name과 동일한지 확인하여 같은 경우에만 필터링
            .filter(member -> member.getName().equals(name))
            .findAny(); // 끝까지 돌렸는데도 찾지 못하면 Optional의 null 반환
  }

  @Override
  public List<Member> findAll() {
    // 자바 실무에서 List 많이 사용
    // 루프 돌리기 용이
    return new ArrayList<>(store.values()); // store에 있는 mamber 모두 반환
  }

  public void clearStore() {
    // store 전체 비우기
    store.clear();
  }
}
