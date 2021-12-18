package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

public class StatefulServiceTest {

  @Test
  void statefulServiceSingleton() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulService statefulService1 = ac.getBean(StatefulService.class);
    StatefulService statefulService2 = ac.getBean(StatefulService.class);

    // ThreadA : A 10000원 주문
//    statefulService1.order("userA", 10000);
    int userAPrice = statefulService1.order("userA", 10000);
    // ThreadA : B 20000원 주문
//    statefulService2.order("userA", 10000);
    int userBPrice = statefulService2.order("userB", 20000);

    // ThreadA : A 주문 금액 조회
//    int price = statefulService1.getPrice();
    // statefulService2가 덮어씌워버림
    System.out.println("price = " + userAPrice); // 기댓값은 10000인데, 실제는 20000

//    assertThat(statefulService1.getPrice()).isEqualTo(20000);
  }

  static class TestConfig {
    @Bean
    public StatefulService statefulService() {
      return new StatefulService();
    }
  }
}
