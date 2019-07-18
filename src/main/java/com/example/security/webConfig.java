package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.service.impl.MyUserDetailsService;

@Configuration
@EnableConfigurationProperties
public class webConfig extends WebSecurityConfigurerAdapter {

	/* @Autowired
	 private CustomAuthenticationProvider authProvider;
	 */
	@Autowired(required=true)
	private MyUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	/*	http.csrf().disable().authorizeRequests().antMatchers("/user/save").permitAll().antMatchers("/user/getAllUser")
				.authenticated();*/
		 http
	      .csrf().disable()
	      .authorizeRequests().anyRequest().authenticated()
	      .and().httpBasic()
	      .and().sessionManagement().disable();
	}

	@Autowired
	public void ConfigurGlobal(AuthenticationManagerBuilder authentication) throws Exception {
		authentication//.authenticationProvider(authenticationProvider());
		.userDetailsService(userDetailsService).passwordEncoder(encoder());
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
