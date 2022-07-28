package com.github.muehmar.security.configuration;

import com.github.muehmar.security.externaluserauth.ExternalHeaderAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ExternalAuthSecurityConfig {
  @Bean
  public SecurityFilterChain externalAuthFilterChain(HttpSecurity http) throws Exception {
    http.antMatcher("/api/v1/admin/**")
        .authorizeHttpRequests(authz -> authz.anyRequest().hasRole("ADMIN"))
        .addFilterBefore(new ExternalHeaderAuthenticationFilter(), BasicAuthenticationFilter.class);
    return http.build();
  }
}
