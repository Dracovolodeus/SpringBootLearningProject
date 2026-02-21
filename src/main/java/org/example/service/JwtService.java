package org.example.service;

import org.example.dto.model.jwt.JwtPair;
import org.example.dto.model.user.UserDetails;
import org.springframework.security.core.Authentication;

public interface JwtService {
    public String generateAccessToken(Authentication authentication);

    public String generateRefreshToken(Authentication authentication);

    public boolean validateToken(String token, UserDetails userDetails);

    public boolean isValidToken(String token, UserDetails userDetails);

    public boolean isRefreshToken(String token);

    public String extractNameFromToken(String token);

    public JwtPair generateJwtPair(Authentication authentication);
}