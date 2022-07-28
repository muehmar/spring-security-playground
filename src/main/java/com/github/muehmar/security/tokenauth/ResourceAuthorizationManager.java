package com.github.muehmar.security.tokenauth;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

public class ResourceAuthorizationManager
    implements AuthorizationManager<RequestAuthorizationContext> {
  private static final Logger logger = LoggerFactory.getLogger(ResourceAuthorizationManager.class);
  private final Pattern pathPattern;

  public ResourceAuthorizationManager(String antMatcher) {
    if (!antMatcher.endsWith("/**")) {
      throw new IllegalArgumentException("AntMatcher must end with '/**'");
    }

    final String regex = antMatcher.substring(0, antMatcher.length() - 2).replace("**", ".+");
    this.pathPattern = Pattern.compile(regex + "([^/]*)");
  }

  @Override
  public AuthorizationDecision check(
      Supplier<Authentication> authentication, RequestAuthorizationContext object) {
    final HttpServletRequest request = object.getRequest();

    final String requestURI = request.getRequestURI();

    final String name = authentication.get().getName();

    return extractUserKey(requestURI)
        .map(key -> key.equals(name))
        .map(AuthorizationDecision::new)
        .orElse(new AuthorizationDecision(false));
  }

  private Optional<String> extractUserKey(String requestUri) {
    final Matcher matcher = pathPattern.matcher(requestUri);
    if (matcher.find()) {
      return Optional.of(matcher.group(1));
    } else {
      logger.warn("Unable to extract the resource key in request URI '{}'", requestUri);
      return Optional.empty();
    }
  }
}
