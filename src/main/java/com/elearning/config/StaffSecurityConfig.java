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

import com.elearning.service.StaffSecurityService;




@Configuration
@SpringBootConfiguration
@Order(2)
public class StaffSecurityConfig {

	@Bean
	public UserDetailsService staffUserDetailsService() {
		return new StaffSecurityService();
	}

	/*@Bean
	public PasswordEncoder passwordEncoder2() {
		return NoOpPasswordEncoder.getInstance();
	}*/
	
	  @Bean
	  public PasswordEncoder passwordEncoder2() {
	    return new BCryptPasswordEncoder();
	  }

	@Bean
	public DaoAuthenticationProvider authenticationProvider2() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(staffUserDetailsService());
		//authProvider.setPasswordEncoder(passwordEncoder2());
		authProvider.setPasswordEncoder(passwordEncoder2());
		return authProvider;
	}

	@Bean
	public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
		http.authenticationProvider(authenticationProvider2());

		http.antMatcher("/staff/**")
			.authorizeRequests().anyRequest().authenticated()
			.and()
			.csrf().disable().cors().disable()
			.formLogin()
				.loginPage("/stafflogin")
				.usernameParameter("email")
				.loginProcessingUrl("/staff/stafflogin")
				.defaultSuccessUrl("/staffindex")
				.permitAll()
			.and()
				.logout()
					.logoutUrl("/staff/logout")
					.logoutSuccessUrl("/");

		return http.build();
	}
}