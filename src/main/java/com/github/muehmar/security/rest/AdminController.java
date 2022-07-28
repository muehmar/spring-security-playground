package com.github.muehmar.security.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/admin")
public class AdminController {
  @GetMapping("/something")
  public ResponseEntity<String> something() {
    final SecurityContext context = SecurityContextHolder.getContext();
    final Authentication authentication = context.getAuthentication();
    return ResponseEntity.ok("Got something for " + authentication.getName());
  }

  @GetMapping("/another-thing")
  public ResponseEntity<String> anotherThing() {
    final SecurityContext context = SecurityContextHolder.getContext();
    final Authentication authentication = context.getAuthentication();
    return ResponseEntity.ok("Got another thing for " + authentication.getName());
  }
}
