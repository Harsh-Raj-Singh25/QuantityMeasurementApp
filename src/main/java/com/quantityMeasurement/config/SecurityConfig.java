package com.quantityMeasurement.config;

import com.quantityMeasurement.security.CustomOAuth2UserService;
import com.quantityMeasurement.security.JwtAuthenticationFilter;
import com.quantityMeasurement.security.OAuth2LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain; 
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;

	@Autowired
	private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.cors(cors -> cors.configure(http)) // ADD THIS LINE TO ALLOW FRONTEND FETCH
		.csrf(AbstractHttpConfigurer::disable)
				// Make the session stateless (since we are using JWTs)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(auth -> auth
						// Allow anyone to access the H2 console, Swagger UI, and Login paths
						.requestMatchers("/h2-console/**", "/swagger-ui/**", "/v3/api-docs/**", "/login/**",
								"/oauth2/**")
						.permitAll()
						// Lock down the entire Quantity Measurement API
						.requestMatchers("/api/v1/quantities/**").authenticated().anyRequest().authenticated())
				.oauth2Login(
						oauth2 -> oauth2.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
								.successHandler(oAuth2LoginSuccessHandler))
				// Fix H2 console framing issues
				.headers(headers -> headers.frameOptions(frame -> frame.disable()));

		// Add our JWT Bouncer before the standard Spring Security filter
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}