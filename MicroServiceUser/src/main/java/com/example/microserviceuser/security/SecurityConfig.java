package com.example.microserviceuser.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



  @Autowired
  private final CustomUserDetailsService userDetailsService;
  @Autowired
  private final JwtTokenProvider jwtTokenProvider;
  @Autowired
  public SecurityConfig(CustomUserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider) {
    this.userDetailsService = userDetailsService;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    System.out.println("im at security filter chain");

    // JWT Authentication filter
    JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtTokenProvider);

    http
      .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (can be adjusted as per requirement)
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/public/**", "/api/auth/**").permitAll() // Public routes accessible to everyone
        .requestMatchers("/api/user/**").hasRole("ADMIN") // Only allow users with ADMIN role to access /user/create
        .anyRequest().authenticated() // All other requests require authentication
      )
      .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add the JWT filter

    return http.build();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
