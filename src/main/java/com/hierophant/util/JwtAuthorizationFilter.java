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
	private JwtToken jwtToken;
	
	@Autowired
	private UserService userService;
	
	private String token = "";
	private String username = "";

	public JwtAuthorizationFilter(JwtToken jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// if the request is OPTIONS we do nothing
		if(request.getMethod().equalsIgnoreCase("OPTIONS")) {
			response.setStatus(200);
		}
		 //GET, POST, DELTE, etc
		else {
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
				}
				if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					log.info("checking");
					Optional<User> userDetails = userService.findByUserName(username);
					log.info("details " + userDetails.get().getUsername());
					if(jwtToken.isTokenValid(userDetails.get().getUsername(), token)) {
						Authentication auth = jwtToken.getAuthentication(username, null, request);
						log.info("auth " + auth);
						SecurityContextHolder.getContext().setAuthentication(auth);
					}
				}
				filterChain.doFilter(request, response);
			}
		}
	}

}
