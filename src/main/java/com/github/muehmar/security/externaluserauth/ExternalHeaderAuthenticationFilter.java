package com.github.muehmar.security.externaluserauth;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class ExternalHeaderAuthenticationFilter extends OncePerRequestFilter {
  private static final String HEADER_LOGIN_NAME = "X-EXTERNAL-LOGIN-NAME";

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    final Optional<String> loginName = Optional.ofNullable(request.getHeader(HEADER_LOGIN_NAME));

    loginName.ifPresent(
        name -> {
          final ExternalUser externalUser = new ExternalUser(name);

          final ExternalUserAuthenticationToken externalUserAuthenticationToken =
              new ExternalUserAuthenticationToken(
                  externalUser, Collections.singletonList(() -> "ROLE_ADMIN"));

          final SecurityContext context = SecurityContextHolder.createEmptyContext();
          context.setAuthentication(externalUserAuthenticationToken);
          SecurityContextHolder.setContext(context);
        });

    filterChain.doFilter(request, response);
  }
}
