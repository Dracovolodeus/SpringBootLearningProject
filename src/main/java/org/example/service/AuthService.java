package org.example.service;

import jakarta.transaction.Transactional;
import org.example.dto.model.jwt.JwtPair;
import org.example.dto.model.jwt.LoginDto;
import org.example.dto.model.jwt.RefreshTokenRequest;
import org.example.dto.model.user.UserDto;
import org.example.dto.model.user.UserRegisterDto;
import org.example.exception.NameOccupiedException;

public interface AuthService {
  @Transactional
  UserDto registerUser(UserRegisterDto user) throws NameOccupiedException;

  public JwtPair login(LoginDto loginDto);

  public JwtPair refreshToken(RefreshTokenRequest request);
}
