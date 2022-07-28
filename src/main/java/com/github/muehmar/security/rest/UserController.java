package com.github.muehmar.security.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {

  @GetMapping("/{key}")
  public ResponseEntity<String> getUser(@PathVariable String key) {
    return ResponseEntity.ok("User with key " + key + " loaded!");
  }
}
