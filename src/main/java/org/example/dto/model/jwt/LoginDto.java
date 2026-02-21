package org.example.dto.model.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class LoginDto {
  @NonNull private String name;
  @NonNull private String password;
}
