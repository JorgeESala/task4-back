package com.itransition.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	

	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	    return authenticationConfiguration.getAuthenticationManager();
	}
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//            http.authorizeHttpRequests(request -> request
//            .requestMatchers("/api/auth/login", "/api/auth/register")
//            .permitAll()
//            .anyRequest()
//            .authenticated()
//            )
//            .sessionManagement(session -> session
//            		.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//            		)
//            .csrf(csrf -> csrf.disable());
//        return http.build();
//    }
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.authorizeHttpRequests(request -> request
            .anyRequest()
            .permitAll()
            )
            .sessionManagement(session -> session
            		.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            		)
            .csrf(csrf -> csrf.disable());
        return http.build();
    }
	

	
	
	
	
}
