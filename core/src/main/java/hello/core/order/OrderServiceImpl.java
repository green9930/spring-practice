package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
//  private final MemberRepository memberRepository = new MemoryMemberRepository();
//  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//  private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

  // OrderServiceImpl이 구현체를 의존하지 않도록 멤버 변수 생성
  private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;

  // @RequiredArgsConstructor에 의해 생성자 자동 생성
  @Autowired
  // 생성자
  public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    // 회원 정보 조회
    Member member = memberRepository.findById(memberId);
    // 정액 할인 정책이든, 정률 할인 정책이든
    // discountPolicy에 할당된 new 뒤의 DiscountPolicy의 구현체만 바꿔주면 됨
    int discountPrice = discountPolicy.discount(member, itemPrice);

    // 최종적으로 생성된 주문 반환
    return new Order(memberId, itemName, itemPrice, discountPrice);
  }
  
  // 테스트 용도
  public MemberRepository getMemberRepository() {
    return memberRepository;
  }
}
