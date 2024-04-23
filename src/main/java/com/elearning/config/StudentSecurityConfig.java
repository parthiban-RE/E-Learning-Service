package com.elearning.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.elearning.service.StudentSecurityService;



@Configuration
@SpringBootConfiguration
@Order(1)

public class StudentSecurityConfig {

	
	@Bean
	public UserDetailsService stuDetailsService() {
		return new StudentSecurityService();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		//return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}

	
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider1() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(stuDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain1(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider1());

		http.authorizeRequests().antMatchers("/").permitAll();

		http.antMatcher("/student/**")
			.authorizeRequests().anyRequest().authenticated()
			.and()
			.csrf().disable().cors().disable()
			.formLogin()
				.loginPage("/login")
					.usernameParameter("email")
					.loginProcessingUrl("/student/login")
					.defaultSuccessUrl("/stuindex")
				.permitAll()
			.and()
			.logout()
				.logoutUrl("/student/logout")
				.logoutSuccessUrl("/");

		return http.build();
	}

}
