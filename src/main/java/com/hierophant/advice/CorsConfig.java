package com.hierophant.advice;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	      CorsConfiguration config = new CorsConfiguration();
	      config.setAllowCredentials(true);
	      config.addAllowedOrigin("*");
	      config.addAllowedHeader("*");
	      config.setAllowedHeaders(Arrays.asList("*"));
	      config.setAllowedOrigins(Arrays.asList("*"));
	      config.setAllowedMethods(Arrays.asList("GET","POST"));

	      source.registerCorsConfiguration("/**", config);
	      return new CorsFilter(source);
	}
}
