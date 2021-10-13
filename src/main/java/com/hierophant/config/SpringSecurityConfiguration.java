package com.hierophant.config;

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

import com.hierophant.service.UserService;
import com.hierophant.util.JwtAuthorizationFilter;

//@Configuration
//@EnableWebSecurity
//implements ApplicationContextAware
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter   {
	
	@Autowired
	private JwtAuthorizationFilter jwtFilter;
	@Autowired
	private UserService userService;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
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
	            "/webjars/**"
	         );
	}
	
	@Override 
	public void configure(HttpSecurity http) throws Exception {
//		http.cors().disable();
//		http.csrf().disable().authorizeRequests()
//		.antMatchers(HttpMethod.POST, "**/users/register").permitAll()
//		.antMatchers(HttpMethod.POST, "/users/authenticate").permitAll()
//		.antMatchers(HttpMethod.POST, "http://localhost:4200/users/register").permitAll()
//		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//		.anyRequest().authenticated()
//		.and().exceptionHandling().and()
//		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		http
        .cors()
      .and()
        .csrf()
        .disable()
        .exceptionHandling()
         .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
     .and()
        .authorizeRequests()
        .antMatchers("/users/authenticate").permitAll()
          .antMatchers("/users/register").permitAll()
          .antMatchers("/posts/findAll").permitAll()
          .antMatchers("/posts/findAllUsers").permitAll()
          .antMatchers("/h2-console/**").permitAll()
          .antMatchers("/swagger-ui/index.html").permitAll()
        .anyRequest().authenticated();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(auth);
		auth.userDetailsService(userService);
	}
	
	// Needed this for "There is no PasswordEncoder mapped for the id "null" " exception
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean(name=BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
