package com.exam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exam.security.JWTAuthenticationEntryPoint;
import com.exam.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

	
	 @Autowired 
	 private JWTAuthenticationEntryPoint point;
	  
	 @Autowired 
	 private JwtAuthenticationFilter filter;
	 

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
        .cors().and() 
                .authorizeHttpRequests(auth->auth.requestMatchers("/users/**").authenticated().requestMatchers("/auth/login","/user/**").permitAll().anyRequest().authenticated())
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
