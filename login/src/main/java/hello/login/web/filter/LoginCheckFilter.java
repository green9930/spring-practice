package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

  private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String requestURI = httpRequest.getRequestURI();
    HttpServletResponse httpResponse = (HttpServletResponse) response;

    try {
      log.info("인증 체크 필터 시작 {}", requestURI);
      // 인증 체크 로직
      if (isLoginCheckPath(requestURI)) {
        log.info("인증 체크 로직 실행 {}", requestURI);
        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
          log.info("미인증 사용자 요청 {}", requestURI);
          // 로그인으로 redirect
          httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
          return;
        }
      }

      chain.doFilter(request, response);

    } catch (Exception e) {
      throw e; // 예외도 로깅할 수 있으나, 서블릿 컨테이너에서 예외를 잡아야 하므로 톰캣까지 예외를 보내주어야 한다.
    } finally {
      log.info("인증 체크 필터 종료 {}", requestURI);
    }
  }

  // whitelist의 경우 인증 체크 X
  private boolean isLoginCheckPath(String requestURI) {
    return ! PatternMatchUtils.simpleMatch(whitelist, requestURI);
  }


}
