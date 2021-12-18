package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentresolver.Login;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

//    @GetMapping("/")
    public String home() {
        return "home";
    }

    // 로그인 처리까지 가능한 홈 화면
//    @GetMapping("/")
    public String homeLogin(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
        if (memberId == null) {
            return "home";
        }

        // 로그인 성공해서 쿠키가 있는 사용자
        Member loginMember = memberRepository.findById(memberId);
        // 로그인한지 오래되어 쿠키가 없어진 경우 등
        if (loginMember == null) {
            return "home";
        }

        // 진짜 성공 로직!
        model.addAttribute("member", loginMember); // 모델 어트리뷰트에 담는다.
        return "loginHome"; // 로그인 사용자 전용 홈으로 보낸다.
    }

//    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model) {

        // 세션 관리자에 저장된 회원 정보 조회
        Member member = (Member)sessionManager.getSession(request);

        // 로그인
        if (member == null) {
            return "home";
        }

        // 진짜 성공 로직!
        model.addAttribute("member", member); // 모델 어트리뷰트에 담는다.
        return "loginHome"; // 로그인 사용자 전용 홈으로 보낸다.
    }

//    @GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "home";
        }

        // 세션 관리자에 저장된 회원 정보 조회
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }

        // 진짜 성공 로직! 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember); // 모델 어트리뷰트에 담는다.
        return "loginHome"; // 로그인 사용자 전용 홈으로 보낸다.
    }

//    @GetMapping("/")
    public String homeLoginV3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {

        // 세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }

        // 진짜 성공 로직! 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember); // 모델 어트리뷰트에 담는다.
        return "loginHome"; // 로그인 사용자 전용 홈으로 보낸다.
    }

    @GetMapping("/")
    public String homeLoginV3ArgumentResolver(@Login Member loginMember, Model model) {

        // 세션에 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }

        // 진짜 성공 로직! 세션이 유지되면 로그인으로 이동
        model.addAttribute("member", loginMember); // 모델 어트리뷰트에 담는다.
        return "loginHome"; // 로그인 사용자 전용 홈으로 보낸다.
    }

}