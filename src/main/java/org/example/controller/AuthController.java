package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.model.jwt.JwtPair;
import org.example.dto.model.jwt.LoginDto;
import org.example.dto.model.jwt.RefreshTokenRequest;
import org.example.dto.model.user.UserDto;
import org.example.dto.model.user.UserRegisterDto;
import org.example.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
  private AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<JwtPair> login(@RequestBody LoginDto loginDto) {
    return ResponseEntity.ok(authService.login(loginDto));
  }

  @PostMapping("/register")
  public UserDto register(@RequestBody UserRegisterDto userRegisterDto) {
    return authService.registerUser(userRegisterDto);
  }

  @PostMapping("/refresh-token")
  public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
    JwtPair jwtPair = authService.refreshToken(request);
    return ResponseEntity.ok(jwtPair);
  }
}
