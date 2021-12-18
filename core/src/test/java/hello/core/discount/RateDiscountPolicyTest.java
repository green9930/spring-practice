package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
  RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

  @Test
  @DisplayName("VIPs get 10% discount")
  void vip_o(){
    // given
    Member member = new Member(1L, "memberVIP", Grade.VIP);
    // when
    int discount = discountPolicy.discount(member, 10000);
    // then
    assertThat(discount).isEqualTo(1000);
  }

  // 실패 테스트도 만들어보기
  @Test
  @DisplayName("If member is not a VIP, discount does not apply")
  void vip_x() {
    // given
    Member member = new Member(2L, "memberBASIC", Grade.BASIC);
    // when
    int discount = discountPolicy.discount(member, 10000);
    // then
    // static import
    assertThat(discount).isEqualTo(1000);
  }

}