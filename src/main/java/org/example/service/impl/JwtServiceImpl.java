package org.example.service.impl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.dto.model.jwt.JwtPair;
import org.example.dto.model.user.UserDetails;
import org.example.entity.UserEntity;
import org.example.repository.UserRepository;
import org.example.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${app.security.jwt.private-key}")
    private RSAPrivateKey jwtPrivateKey;

    @Value("${app.security.jwt.public-key}")
    private RSAPublicKey jwtPublicKey;

    @Value("${app.security.jwt.access-exp}")
    private long jwtAccessExp;
    @Value("${app.security.jwt.refresh-exp}")
    private long jwtRefreshExp;
    private final String jwtAccessTokenType = "access";
    private final String jwtRefreshTokenType = "refresh";

    @Autowired
    private UserRepository userRepository;

    public static final String TOKEN_PREFIX = "Bearer";

    @Override
    public String generateAccessToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("type", jwtAccessTokenType);
        return generateToken(authentication, jwtAccessExp, claims);
    }

    @Override
    @Transactional
    public String generateRefreshToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("type", jwtRefreshTokenType);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = generateToken(authentication, jwtRefreshExp, claims);
        UserEntity userEntity = userDetails.getUserEntity();
        userEntity.setRefreshToken(token);
        userRepository.save(userEntity);
        return token;
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        final String name = extractNameFromToken(token);

        if (!name.equals(userDetails.getUsername())) {
            return false;
        }
        try {
            Jwts.parser().verifyWith(jwtPublicKey).build().parseSignedClaims(token);
            return true;
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean isValidToken(String token, UserDetails userDetails) {
        return Jwts.parser()
                .verifyWith(jwtPublicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload() != null;
    }

    @Override
    public boolean isRefreshToken(String token) {
        Map<String, Object> claims = Jwts.parser()
                .verifyWith(jwtPublicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return jwtRefreshTokenType.equals(claims.get("type"));
    }

    @Override
    public String extractNameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(jwtPublicKey)
                .build()
                .parseClaimsJws(token)
                .getPayload()
                .getSubject();
    }

    @Override
    public JwtPair generateJwtPair(Authentication authentication) {
        return new JwtPair(
                generateRefreshToken(authentication),
                generateAccessToken(authentication)
        );
    }

    private String generateToken(Authentication authentication, long exp, Map<String, Object> claims) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date nowDate = new Date();
        Date expDate = new Date(nowDate.getTime() + exp);

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(claims)
                .issuedAt(nowDate)
                .expiration(expDate)
                .signWith(jwtPrivateKey, SignatureAlgorithm.RS256)
                .compact();
    }
}