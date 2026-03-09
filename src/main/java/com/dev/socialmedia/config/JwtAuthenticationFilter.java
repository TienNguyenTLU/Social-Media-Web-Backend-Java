package com.dev.socialmedia.config;

import com.dev.socialmedia.services.JWTService;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwt;
    public JwtAuthenticationFilter(JWTService jwt) {
        this.jwt = jwt;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer"))
         {
             String token = authHeader.substring(7);
             try
             {
                 if(jwt.validateToken(token))
                 {
                     String username = jwt.getUsernameFromToken(token);
                     request.setAttribute("authenticatedUser", username);
                 }
             }catch (Exception e)
             {
                 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                 response.getWriter().write("Token không hợp lệ hoặc đã hết hạn");
                 return;
             }
         }
         filterchain.doFilter(request, response);
    }
}
