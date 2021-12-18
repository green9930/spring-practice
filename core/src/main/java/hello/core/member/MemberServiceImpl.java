package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// 멤버 서비스 (회원 가입 / 회원 조회) 인터페이스
public class MemberServiceImpl implements MemberService {

  // 회원 가입하고 조회하려면 MemberRepository가 필요
  // new MemoryMemberRepository()로 구현체 선택
//  private final MemberRepository memberRepository = new MemoryMemberRepository();
  private final MemberRepository memberRepository;

  @Autowired
  // memberRepository 안에 어떤 구현체가 들어갈지 생성자를 통해 결정
  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  // 다형성에 의해 인터페이스가 아닌 MemoryMemberRepository에 있는 save 호출
  @Override
  public void join(Member member) {
    memberRepository.save(member);
  }

  @Override
  public Member findMember(Long memberId) {
    return memberRepository.findById(memberId);
  }

  // 테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
