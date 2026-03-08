package com.dev.socialmedia.controllers;

import com.dev.socialmedia.services.AuthService;
import com.dev.socialmedia.services.JWTService;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String login(String username, String password) {
        return authService.login(username, password);
    }
}
