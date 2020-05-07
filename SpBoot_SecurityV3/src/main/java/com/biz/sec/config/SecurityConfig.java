package com.biz.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import com.biz.sec.service.SecurityUserServiceImpl;

import lombok.RequiredArgsConstructor;

/*
 * Spring Sec customize 1st proc
 * security-context.xml에 설정했던 옵션들을 지정하는 클래스
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private final SecurityUserServiceImpl sUserService;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		
		//react와 연동하기위한 spring sec 설정
		httpSecurity.csrf().disable();
		httpSecurity.httpBasic().disable();
		httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		httpSecurity.authorizeRequests().antMatchers("/hello").permitAll()
		.antMatchers("/login").permitAll()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user/login").permitAll()
		.antMatchers("/**").permitAll();
		
		httpSecurity.authorizeRequests().and().formLogin().loginPage("/user/login")
		.failureUrl("/user/login?error").usernameParameter("username").passwordParameter("password")
		.and().logout().logoutSuccessUrl("/");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(auth);
		auth.userDetailsService(sUserService).passwordEncoder(sUserService.getPasswordEncoder());
	}

	/*
	 * html template 파일에서 sec:tag를 사용할때 값,설정,함수등을 사용할수 있도록 객체를 전달하는 용도
	 * thymleaf-springsec5 에서는 Security 설정된 Config에서 자동 주입이 된다.
	 * 특히 config class를 WebSecurityConfigurerAdapter 상속 받아서 작성할경우
	 * 굳이 bean으로 설정하지 않아도 된다
	 */
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(web);
		web.ignoring().antMatchers("/static/css/**","/static/js/**").antMatchers("/static/images/**")
		.antMatchers("/static/music/**");
	}
	
}
