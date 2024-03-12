package com.example.springbootdemo.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("/user")
    @PreAuthorize("isFullyAuthenticated()")
    public HttpEntity<String> user() {
        return ResponseEntity.ok("User page");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public HttpEntity<String> admin() {
        return ResponseEntity.ok("Admin page");
    }
}
