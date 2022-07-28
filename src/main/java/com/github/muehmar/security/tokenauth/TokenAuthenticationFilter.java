package com.github.muehmar.security.tokenauth;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
  private static final String TOKEN_HEADER = "X-LOGIN-TOKEN";

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    final Optional<String> token = Optional.ofNullable(request.getHeader(TOKEN_HEADER));

    token.ifPresent(
        t -> {
          final UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
              new UsernamePasswordAuthenticationToken(
                  t, t, Collections.singletonList(() -> "ROLE_USER"));

          final SecurityContext context = SecurityContextHolder.createEmptyContext();
          context.setAuthentication(usernamePasswordAuthenticationToken);
          SecurityContextHolder.setContext(context);
        });

    filterChain.doFilter(request, response);
  }
}
