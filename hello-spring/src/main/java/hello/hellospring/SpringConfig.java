package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;


@Configuration
public class SpringConfig {

//  private DataSource dataSource;
//
//  @Autowired
//  public SpringConfig(DataSource dataSource) {
//    this.dataSource = dataSource;
//  }

//  private EntityManager em;

//  @Autowired
//  public SpringConfig(EntityManager em) {
//    this.em = em;
//  }

  private final MemberRepository memberRepository;

  @Autowired
  public SpringConfig(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Bean
  // memberService의 매개변수로 memberRepository 전달 필요
  public MemberService memberService() {
    return new MemberService(memberRepository );
  }

//  @Bean
//  public TimeTraceAop timeTraceAop(){
//    return new TimeTraceAop();
//  }

//  @Bean
//  public MemberRepository memberRepository() {
//    return new MemoryMemberRepository();
//    return new JdbcMemberRepository(dataSource);
//    return new JdbcTemplateMemberRepository(dataSource);
//    return new JpaMemberRepository(em);
//  }

}
