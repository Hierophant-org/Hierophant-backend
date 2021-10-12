package com.hierophant.util;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.hierophant.controller.UserController;

@Component
public class JwtToken {
	// Might need to change this code;
	private String secret = "9nEWPTThWQezy8TUvJDnw4cfl5SXIFAsITkXkccvMDU9pxnzcxMdM5Qdq7PNNziFgpD7VwTHiumrInxzWV68tMf7xbnGUYQucbTy";
	private static Logger log = LoggerFactory.getLogger(JwtToken.class);
	// Generate a token with Auth0 logic
//	public String generateJwtToken(UserPrincipal userPrincipal) {
//		String[] claims = getClaimsFromUser(userPrincipal); // If the claim is valid, we then will generate a token for user
//		return JWT.create()
//				.withIssuer(Security.HIEROPHANT)
//				.withAudience(Security.GET_ARRAY_ADMINISTRATION)
//				.withIssuedAt(new Date())
//				.withSubject(userPrincipal.getUsername())
//				.withArrayClaim(Security.AUTHORITIES, claims)
//				.withExpiresAt(new Date(System.currentTimeMillis() + Security.EXPIRATION_TIME))
//				.sign(Algorithm.HMAC512(secret.getBytes()));	
//	}
	
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
	
//	private String[] getClaimsFromUser(UserPrincipal userPrincipal) {
//		List<String> authorities = new ArrayList<>();
//		for(GrantedAuthority grantedAuthority: userPrincipal.getAuthorities()) {
//			authorities.add(grantedAuthority.getAuthority());
//		}
//		return authorities.toArray(new String[0]);
//	}
	
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
		UsernamePasswordAuthenticationToken userPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities); 
		userPasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		return userPasswordAuthenticationToken;
	}
	
	public String getSubject (String token) {
		JWTVerifier verifier = getJWTVerifier();
		return verifier.verify(token).getSubject();
	}

	// The stream caused an error no idea why
//	public List<GrantedAuthority> getAuthorities(String token) {
//		String[] claims = getClaimsFromToken(token);
//		return Stream.of(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList()); // have to use Stream.of() instead of stream()
//	}
	
//	private String[] getClaimsFromToken(String token) {
//		JWTVerifier verifier = getJWTVerifier();
//		return verifier.verify(token).getClaim(Security.AUTHORIZATION).asArray(String.class);
//	}

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
