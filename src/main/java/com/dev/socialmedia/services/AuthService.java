package com.dev.socialmedia.services;

import com.dev.socialmedia.models.User;
import com.dev.socialmedia.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userrepo;
    private final PasswordEncoder encoder;
    private final JWTService jwt;


    public AuthService(UserRepository userrepo, PasswordEncoder encoder, JWTService jwt) {
        this.userrepo = userrepo;
        this.encoder = encoder;
        this.jwt = jwt;
    }
    public String register(String username, String email, String password) {
        if (userrepo.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }
        if (userrepo.existsByEmail(email)) {
            throw new RuntimeException("Email already exists");
        }
        var user = new com.dev.socialmedia.models.User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(encoder.encode(password));
        userrepo.save(user);
        return jwt.GenerateToken(username);
    }
    public String login(String username, String password) {
        var userOpt = userrepo.findByUsername(username);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("Invalid username or password");
        }
        var user = userOpt.get();
        if (!encoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }
        return jwt.GenerateToken(username);
    }
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        String username = authentication.getName();
        return userrepo.findByUsername(username).orNull();
    }
}
