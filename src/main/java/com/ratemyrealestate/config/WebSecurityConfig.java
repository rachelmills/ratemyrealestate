package com.ratemyrealestate.config;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Resource
	private UserDetailsService userDetailsService;  // need a userDetailsService in application context
	
	@Value("${rememberMe.privateKey}")
	private String rememberMeKey;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		logger.info("Creating a password encoder bean");
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public RememberMeServices rememberMeServices() {
		System.out.println("remember me key is " + rememberMeKey);
		TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(rememberMeKey, userDetailsService);
		return rememberMeServices;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()   //users do not need to log in to access any of these urls
		.antMatchers(
					"/home",
					"/error",
					"/login",
					"/newuser",
					"/createuser",
					"/usercreated",
					"/ratings",
					"/allratings",
					"/forgot-password",
					"/reset-password",
					"/search/**",
					"/findagents",
					"/agents",
					"/about",
					"/denied",
					"/loggedout",
					"/pages/**",
					"/static/**",
					"/public/**").permitAll()
		.anyRequest().authenticated();
		
		http.formLogin()
		.loginPage("/login")
				.defaultSuccessUrl("/home")
				.permitAll().and()
				.rememberMe().key(rememberMeKey).rememberMeServices(rememberMeServices()).and()

		.logout()
			.permitAll();				
	}	

	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}
