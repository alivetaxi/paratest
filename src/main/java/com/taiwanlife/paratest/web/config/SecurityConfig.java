package com.taiwanlife.paratest.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.taiwanlife.paratest.web.security.FinAuthenticationProvider;
import com.taiwanlife.paratest.web.security.FinUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(
						"/login",
						"/h2-console/**",
						"/css/**",
						"/js/**",
						"/webfonts/**"
						).permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login").permitAll()
				.defaultSuccessUrl("/index")
				.and()
			.logout().permitAll()
				.and()
			.csrf().disable()
			.headers().frameOptions().disable();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		FinAuthenticationProvider authenticationProvider = new FinAuthenticationProvider();
		authenticationProvider.setUserDetailsService(finUserDetailsService());
		authenticationProvider.setHideUserNotFoundExceptions(false);
		return authenticationProvider;
	}

	@Bean
	public UserDetailsService finUserDetailsService() {
		return new FinUserDetailsService();
	}

}
