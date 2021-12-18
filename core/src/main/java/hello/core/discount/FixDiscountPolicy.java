package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy {

  // 정해진 금액 할인
  private int discountFixAmount = 2000;

  @Override
  public int discount(Member member, int price) {
    return member.getGrade() == Grade.VIP ? discountFixAmount : 0;
  }
}
