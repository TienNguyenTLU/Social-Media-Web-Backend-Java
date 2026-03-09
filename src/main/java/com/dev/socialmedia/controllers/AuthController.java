package com.dev.socialmedia.controllers;

import com.dev.socialmedia.dto.LoginRequest;
import com.dev.socialmedia.dto.RegisterRequest;
import com.dev.socialmedia.services.AuthService;
import com.dev.socialmedia.services.JWTService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final JWTService jwt;

    public AuthController(AuthService authService, JWTService jwt) {
        this.authService = authService;
        this.jwt = jwt;
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest req) {
        return authService.login(req);
    }
    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest req) {
        authService.register(req);
        return ResponseEntity.ok("User registered successfully").toString();
    }
}
