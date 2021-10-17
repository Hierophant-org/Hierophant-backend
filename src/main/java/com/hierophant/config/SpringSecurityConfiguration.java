package com.hierophant.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hierophant.service.UserService;
import com.hierophant.util.JwtAuthorizationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JwtAuthorizationFilter jwtFilter;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		//configure what url's are allowed
		web.ignoring().antMatchers(
	            "/*.html",
	            "/favicon.ico",
	            "/**/*.html",
	            "/**/*.css",
	            "/**/*.js",
	            "/h2-console/**",
	            "/swagger-resources/**",
	            "/swagger-ui.html",
	            "/v2/api-docs",
	            "/webjars/**",
	            "/actuator/health"
	         );
	}
	
	@Override 
	public void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().authorizeRequests()
        .antMatchers("/users/authenticate").permitAll()
        .antMatchers("/users/register").permitAll()
        .antMatchers("/posts/findAll").permitAll()
        .antMatchers("/posts/findAllUsers").permitAll()
        .antMatchers("/comments/findUser").permitAll()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers("/swagger-ui/index.html").permitAll()
          //.antMatchers("/images/**").permitAll()
        .antMatchers("/actuator/health").permitAll()
        .anyRequest().authenticated();
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("configring Authentication Manager");
		auth.userDetailsService(userService);
	}
	
	// Needed this for "There is no PasswordEncoder mapped for the id "null" " exception
	@Bean
	public PasswordEncoder passwordEncoder() {
		//unused password encoder(future goal)
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean(name=BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		//mangage the auth manager
		log.info("authentication manger running");
		return super.authenticationManagerBean();
	}
}
