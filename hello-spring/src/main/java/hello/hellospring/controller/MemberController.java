package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
  // 이 방식도 가능하지만
//  private final MemberService memberService = new MemberService();
  // Spring이 관리하면 Spring Container에 등록하고 전달받아서 사용하는 방식으로 변경해야 한다.
  // MemberService는 하나만 생성해서 공유하는 것이 낫기 때문

  // Spring Container에 등록
  private final MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/members/new")
  public String createForm() {
    return "members/createMemberForm";
  }

  @PostMapping("members/new")
  public String create(MemberForm form) {
    Member member = new Member();
    member.setName(form.getName());

    // view의 input에서 Hello!라고 입력 후 제출
//    System.out.println("member = " + member.getName()); // member = Hello!

    // 회원가입
    memberService.join(member);
    // 회원가입이 끝나면 홈 화면으로 돌아가기
    return "redirect:/";
  }

  @GetMapping("/members")
  public String list(Model model) {
    List<Member> members = memberService.findMembers();
    model.addAttribute("members", members);

    return "members/memberList";
  }
}
