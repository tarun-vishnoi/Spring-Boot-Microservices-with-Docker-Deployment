package com.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		LOGGER.info("SecurityConfig :: configure(AuthenticationManagerBuilder) :: START");
		auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
		LOGGER.info("SecurityConfig :: configure(AuthenticationManagerBuilder) :: END");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		LOGGER.info("SecurityConfig :: getPasswordEncoder() :: START");
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		LOGGER.info("SecurityConfig :: configure(HttpSecurity) :: START");
		http.httpBasic().and().authorizeRequests().antMatchers("/actuator/**").hasRole("ADMIN").antMatchers("/")
				.permitAll().and().csrf().disable().formLogin();
		LOGGER.info("SecurityConfig :: configure(HttpSecurity) :: END");
	}
}
