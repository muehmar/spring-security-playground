package com.github.muehmar.security.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/public")
public class PublicController {

  @GetMapping("/hello")
  public ResponseEntity<String> hello() {
    return ResponseEntity.ok("Hello World!");
  }
}
