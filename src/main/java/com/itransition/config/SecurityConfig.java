package com.itransition.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	

	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors(cors -> cors
		        .configurationSource(request -> {
		            CorsConfiguration config = new CorsConfiguration();
		            config.setAllowedOrigins(List.of("https://task4-jorge.netlify.app"));
		            config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
		            config.setAllowCredentials(true);
		            config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
		            return config;
		        }))
		        .authorizeHttpRequests(request -> request
		            .anyRequest()
		            .permitAll())
		        .sessionManagement(session -> session
		            .sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
		        .csrf(csrf -> csrf.disable());
		    return http.build();
    }
	@Bean
	UrlBasedCorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(Arrays.asList("https://task4-jorge.netlify.app"));
	    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
	    configuration.setAllowCredentials(true);
	    configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
	    
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}

	
	
	
	
}
