package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {
  private Long id; // DB에 저장되고 관리되는 ID

  @NotEmpty
  private String loginId; // 사용자가 입력하는 로그인 ID

  @NotEmpty
  private String name; // 사용자 이름

  @NotEmpty
  private String password; // 사용자 비밀번호

}
