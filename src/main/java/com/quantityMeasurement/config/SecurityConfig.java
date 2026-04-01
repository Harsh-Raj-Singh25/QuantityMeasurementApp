package com.quantityMeasurement.config;

import com.quantityMeasurement.security.CustomOAuth2UserService;
import com.quantityMeasurement.security.JwtAuthenticationFilter;
import com.quantityMeasurement.security.OAuth2LoginSuccessHandler;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
		http.cors(cors -> cors.configurationSource(corsConfigurationSource())) // Enable CORS
				.csrf(csrf -> csrf.disable()) // Disable CSRF for REST APIs
				.authorizeHttpRequests(auth -> auth
						// 1. Let EVERYONE (Guests) access the math calculator endpoints
						.requestMatchers("/api/v1/quantities/**").permitAll()

						// 2. Allow auth endpoints to be public
						.requestMatchers("/oauth2/**", "/login/**").permitAll()

						// 3. Keep History and Profiles locked down to authenticated users only
						.requestMatchers("/api/users/**").authenticated()

						.anyRequest().authenticated())
		// Keep your existing OAuth2 and JWT filter configurations here below...
				.oauth2Login(
						oauth2 -> oauth2.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
								.successHandler(oAuth2LoginSuccessHandler))
				// Fix H2 console framing issues
				.headers(headers -> headers.frameOptions(frame -> frame.disable()))
		;
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	// Explicitly tell Spring Boot to accept traffic from Angular port 4200
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}