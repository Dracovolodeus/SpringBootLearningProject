package org.example.dto.model.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtPair {
  private String refreshToken;
  private String accessToken;
}
