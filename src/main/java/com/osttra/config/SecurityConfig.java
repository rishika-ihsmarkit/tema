package com.osttra.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.osttra.service.CustomUserDetailsService;
import com.osttra.service.CustomUserGroupDetailsService;
import com.osttra.to.CustomUserDetails;


@Configuration
@EnableWebSecurity
@Transactional
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
//	@Autowired
//	JWTAuthenticationFilter jwtAuthenticationFilter;
	

	
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("inside first configurer...");
		http
			.csrf().disable()
				.authorizeHttpRequests()
				.antMatchers("/**").permitAll()
//				.antMatchers("/token").permitAll()
//				.antMatchers("/users/**").permitAll()
					.anyRequest()
						.authenticated()
							.and()
								.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(customUserDetailsService).passwordEncoder(this.passwordEncoder());

	}
	
	@Bean
    public CustomUserGroupDetailsService customUserGroupDetailsService() {
        return new CustomUserGroupDetailsService();
	}
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}