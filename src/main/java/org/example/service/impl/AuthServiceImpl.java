package org.example.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.dto.converter.UserConverter;
import org.example.dto.model.jwt.JwtPair;
import org.example.dto.model.jwt.LoginDto;
import org.example.dto.model.jwt.RefreshTokenRequest;
import org.example.dto.model.user.UserDetails;
import org.example.dto.model.user.UserDto;
import org.example.dto.model.user.UserRegisterDto;
import org.example.entity.UserEntity;
import org.example.exception.NameOccupiedException;
import org.example.repository.UserRepository;
import org.example.service.AuthService;
import org.example.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private JwtService jwtService;
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImpl userDetailsService;
    private PasswordEncoder encoder;

    @Override
    @Transactional
    public UserDto registerUser(UserRegisterDto user) throws NameOccupiedException {
        if (userRepository.existsByName(user.getName())) {
            throw new NameOccupiedException("User with name " + user.getName() + " exists.");
        }
        UserEntity userEntity =
                UserEntity.builder()
                        .name(user.getName())
                        .password(encoder.encode(user.getPassword()))
                        .role("user")
                        .build();
        userRepository.save(userEntity);
        return UserConverter.toDto(userEntity);
    }

    public JwtPair login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getName(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtService.generateJwtPair(authentication);
    }

    public JwtPair refreshToken(RefreshTokenRequest request) {

    String refreshToken = request.getRefreshToken();
    // check if it is valid refresh token
    if(!jwtService.isRefreshToken(refreshToken)) {
        throw new IllegalArgumentException("Invalid refresh token");
    }

    String user = jwtService.extractNameFromToken(refreshToken);
    UserDetails userDetails = (UserDetails) userDetailsService.loadUserByUsername(user);

    if (userDetails == null) {
        throw new IllegalArgumentException("User not found");
    }

    UsernamePasswordAuthenticationToken authentication =
            new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

    String accessToken = jwtService.generateAccessToken(authentication);
    return new JwtPair(accessToken, refreshToken);
}
}
