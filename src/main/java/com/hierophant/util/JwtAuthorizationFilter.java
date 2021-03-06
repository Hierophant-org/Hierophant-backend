package com.hierophant.util;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hierophant.HierophantApplication;
import com.hierophant.model.User;
import com.hierophant.service.UserService;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter{
	
	private static Logger log = LoggerFactory.getLogger(HierophantApplication.class);

	@Autowired
	private JwtToken jwtToken;//token
	
	@Autowired
	private UserService userService;//user service
	
	private String token = "";
	private String username = "";

	public JwtAuthorizationFilter(JwtToken jwtToken) {
		//filter from backend to frountend
		super();
		this.jwtToken = jwtToken;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// if the request is OPTIONS we do nothing
		if(request.getMethod().equalsIgnoreCase("OPTIONS")) {
			//if options
			response.setStatus(200);
		}
		 //GET, POST, DELETE, etc
		else {
			//if delete
			String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			if(authorizationHeader == null || !authorizationHeader.startsWith(Security.TOKEN_PREFIX)) {
				log.warn("Hello no token nor token without bearer");
				filterChain.doFilter(request, response);
				return;
			}
			else {
				log.info("Yes token");
				if (authorizationHeader != null || authorizationHeader.startsWith(Security.TOKEN_PREFIX)) {
					token = authorizationHeader.substring(7);
					username = jwtToken.getSubject(token);
					if(username.isEmpty()) {
						log.error("no username");
						return;
					}
				}
				if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					//if it needs checking
					log.info("checking");
					Optional<User> userDetails = userService.findByUserName(username);
					if(jwtToken.isTokenValid(userDetails.get().getUsername(), token)) {
						Authentication auth = jwtToken.getAuthentication(username, null, request);
						SecurityContextHolder.getContext().setAuthentication(auth);
					}
				}
				filterChain.doFilter(request, response);
			}
		}
	}

}
