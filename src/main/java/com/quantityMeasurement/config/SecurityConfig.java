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
		http.cors(cors -> cors.configurationSource(corsConfigurationSource())).csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						// 1. PUBLIC: Swagger and API Docs (Fixes your Swagger UI failure)
						.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

						// 2. PUBLIC: Math calculator endpoints for Guests
						.requestMatchers("/api/v1/quantities/**").permitAll()

						// 3. PUBLIC: Auth endpoints
						.requestMatchers("/oauth2/**", "/login/**").permitAll()

						// 4. PRIVATE: History and Profiles
						.requestMatchers("/api/users/**").authenticated()

						.anyRequest().authenticated())
				.oauth2Login(
						oauth2 -> oauth2.userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
								.successHandler(oAuth2LoginSuccessHandler))
				.headers(headers -> headers.frameOptions(frame -> frame.disable()));

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();

		// Pull the Vercel URL from Railway environment variables
		String allowedOrigin = System.getenv("ALLOWED_ORIGINS");
		if (allowedOrigin == null || allowedOrigin.isEmpty()) {
			allowedOrigin = "http://localhost:4200";
		}

		configuration.setAllowedOrigins(Arrays.asList(allowedOrigin));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		// Added X-Requested-With and Cache-Control for better frontend compatibility
		configuration
				.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Cache-Control", "X-Requested-With"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}