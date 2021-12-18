package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

//  MemberService memberService = new MemberServiceImpl();
  MemberService memberService;

  // 테스트 실행 전에 appConfig 생성하고 memberService 할당
  // 이후 테스트 실행
  @BeforeEach
  public void beforeEach() {
    AppConfig appConfig = new AppConfig();
    memberService = appConfig.memberService();
  }

  @Test
  void join() {
    // given
    Member member = new Member(1L, "memberA", Grade.VIP);

    // when
    memberService.join(member);
    Member findMember = memberService.findMember(1L);

    // then
    // join()한 member와 findMember가 같은지 확인
    Assertions.assertThat(member).isEqualTo(findMember);
  }
}
