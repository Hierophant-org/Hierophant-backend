package com.hierophant.util;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Component
@Configuration
public class JwtToken {
	private static Logger log = LoggerFactory.getLogger(JwtToken.class);
	// Might need to change this code;
	@Value("${secret}")
	private String secret;
	
	
	public String generateJwtToken(String username) {
		log.info("generating a token");
		return JWT.create()
				.withIssuer(Security.HIEROPHANT)
				.withAudience(Security.GET_ARRAY_ADMINISTRATION)
				.withIssuedAt(new Date())
				.withSubject(username)
				.withExpiresAt(new Date(System.currentTimeMillis() + Security.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(secret.getBytes()));	
	}
	
	// ================================================================================================================================================================
	
	// Below are verify token methods
	
	public boolean isTokenValid(String username, String token) {
		JWTVerifier verifier = getJWTVerifier();
		return !username.isEmpty() && !isTokenExpired(verifier,token);
	}
	private boolean isTokenExpired(JWTVerifier verifier, String token) {
		Date expiration = verifier.verify(token).getExpiresAt();
		return expiration.before(new Date());
	}
	
	@Transactional
	public Authentication getAuthentication(String username, List<GrantedAuthority> authorities, HttpServletRequest request) {
		//grab authentication
		UsernamePasswordAuthenticationToken userPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities); 
		userPasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		return userPasswordAuthenticationToken;
	}
	
	public String getSubject (String token) {
		//get the subject
		JWTVerifier verifier = getJWTVerifier();
		return verifier.verify(token).getSubject();
	}

	private JWTVerifier getJWTVerifier() {
		JWTVerifier verifier;
		try {
			Algorithm algorithm = Algorithm.HMAC512(secret);
			verifier = JWT.require(algorithm).withIssuer(Security.HIEROPHANT).build();
		} catch(JWTVerificationException e) {
			throw new JWTVerificationException(Security.TOKEN_CANNOT_BE_VERIFIED);
		}
		return verifier;
	}

	
}
