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
	      //allow any header or origins
	      config.setAllowedHeaders(Arrays.asList("*"));
	      config.setAllowedOrigins(Arrays.asList("*"));
	      //allow get and post methods
	      config.setAllowedMethods(Arrays.asList("GET","POST"));

	      //allow at
	      //source.registerCorsConfiguration("http://localhost:5000/hierophant/**", config);
	      source.registerCorsConfiguration("http://hierophantbackendpipe-env.eba-v2pxdpwf.us-east-1.elasticbeanstalk.com/hierophant/**", config);
	      return new CorsFilter(source);

	}
}