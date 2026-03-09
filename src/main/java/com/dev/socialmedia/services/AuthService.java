package com.dev.socialmedia.services;
import com.dev.socialmedia.dto.LoginRequest;
import com.dev.socialmedia.dto.RegisterRequest;
import com.dev.socialmedia.models.User;
import com.dev.socialmedia.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userrepo;
    private final JWTService jwt;


    public AuthService(UserRepository userrepo, JWTService jwt) {
        this.userrepo = userrepo;
        this.jwt = jwt;
    }
    public String register(RegisterRequest req) {
        if (userrepo.existsByUsername(req.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userrepo.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        var user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(BCrypt.hashpw(req.getPassword(), BCrypt.gensalt()));
        userrepo.save(user);
        return jwt.GenerateToken(req.getUsername());
    }
    public String login(LoginRequest req) {
        var userOpt = userrepo.findByUsername(req.getUsername());
        var hashed = BCrypt.hashpw(req.getPassword(), BCrypt.gensalt());
        if (!userOpt.isPresent()) {
            throw new RuntimeException("Invalid username or password");
        }
        var user = userOpt.get();
        if (!BCrypt.checkpw(req.getPassword(), hashed)) {
            throw new RuntimeException("Invalid username or password");
        }
        return jwt.GenerateToken(req.getUsername());
    }
}
