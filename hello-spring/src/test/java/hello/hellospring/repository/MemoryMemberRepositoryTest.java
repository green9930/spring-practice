package hello.hellospring.repository;

import hello.hellospring.domain.Member;
//import org.junit.jupiter.api.Assertions
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

// 테스트용이기 때문에 굳이 public으로 선언할 필요 없음
class MemoryMemberRepositoryTest {
  MemoryMemberRepository repository = new MemoryMemberRepository();

  // 메서드 실행이 끝날 때마다 store을 비우는 일을 수행하는 콜백 메서드
  @AfterEach
  public void afterEach(){
    repository.clearStore();
  }
  
  // @Test 어노테이션으로 실행 가능
  @Test
  public  void save() {
    Member member = new Member();
    member.setName("spring");
    
    // repository에 member 저장
    repository.save(member);

    // 확인
    Member result = repository.findById(member.getId()).get();

    // new 키워드로 만든 member와 DB에서 꺼낸 member가 같으면 true
    // System.out.println()을 이욯해서 테스트하는 방법
//    System.out.println("result = " + (result == member)); // result = true
    // (import org.junit.jupiter.api.Assertions;)
    // Assertions 이용해서 테스트하는 방법
//    Assertions.assertEquals(result, member);
    // (import org.assertj.core.api.Assertions;)
    assertThat(member).isEqualTo(result);
  }

  @Test
  public void findByName() {

    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    Member result = repository.findByName("spring1").get();

    assertThat(result).isEqualTo(member1); // 정상 작동
    assertThat(result).isEqualTo(member2); // 에러 발생
  }

  @Test
  public void findAll() {

    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    List<Member> result = repository.findAll();

    assertThat(result.size()).isEqualTo(2);

  }
}
