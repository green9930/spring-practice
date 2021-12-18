package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {
  // @Slf4j에서 아래 코드를 자동으로 넣어줌
  // private final Logger log = LoggerFactory.getLogger(getClass());

  @GetMapping("/log-test")
  public String logTest() {
    String name = "Spring";

    // System.out.println("name = " + name);

    log.trace("trace log={}", name);
    log.debug("debug log={}", name); // 개발 단계에서 확인
    log.info("info log={}", name); // 중요한 비즈니스 정보
    log.warn("warn log={}", name); // 경고
    log.error("error log={}", name); // 에러 확인 필요
    return "ok";
  }
}
