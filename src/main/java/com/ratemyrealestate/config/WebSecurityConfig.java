package com.ratemyrealestate.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Resource
	private UserDetailsService userDetailsService;  // need a userDetailsService in application context
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()   //users do not need to log in to access any of these urls
		.antMatchers("/",
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
			.permitAll().and()
		.logout()
			.permitAll();				
	}
	
	@Autowired
	@Override
	protected void  configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new StandardPasswordEncoder();
	}
	
	

}
