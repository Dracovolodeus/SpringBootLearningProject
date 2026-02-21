package org.example.dto.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  private long id;
  private String name;
  private String role;
  @JsonIgnore
  private String password;
  @JsonIgnore
  private String refreshToken;
}
