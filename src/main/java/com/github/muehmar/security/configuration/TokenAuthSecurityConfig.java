package com.github.muehmar.security.configuration;

import com.github.muehmar.security.tokenauth.ResourceAuthorizationManager;
import com.github.muehmar.security.tokenauth.TokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class TokenAuthSecurityConfig {
  @Bean
  public SecurityFilterChain tokenAuthFilterChain(HttpSecurity http) throws Exception {
    http.antMatcher("/api/v1/users/**")
        .authorizeHttpRequests(
            authz -> authz.anyRequest().access(new ResourceAuthorizationManager("/api/v1/users/**")))
        .addFilterBefore(new TokenAuthenticationFilter(), BasicAuthenticationFilter.class);
    return http.build();
  }
}
