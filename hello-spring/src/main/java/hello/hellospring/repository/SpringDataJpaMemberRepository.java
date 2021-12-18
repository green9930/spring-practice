package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Spring data JPA가 SpringDataJpaMemberRepository 인터페이스를 보고
// 구현체를 자동으로 등록해준다.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

  @Override
  Optional<Member> findByName(String name);
}
