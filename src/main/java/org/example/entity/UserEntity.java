package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = UserEntity.TABLE_NAME)
public class UserEntity {

  public static final String TABLE_NAME = "users";
  public static final String COLUMN_ID_NAME = "id";
  public static final String COLUMN_NAME_NAME = "name";
  public static final String COLUMN_ROLE_NAME = "role";
  public static final String COLUMN_PASSWORD_NAME = "password";
  public static final String COLUMN_REFRESH_TOKEN_NAME = "refresh_token";

  @Id
  @Column(name = COLUMN_ID_NAME, nullable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = COLUMN_NAME_NAME, nullable = false)
  private String name;

  @Column(name = COLUMN_PASSWORD_NAME, nullable = false, unique = true)
  private String password;

  @Column(name = COLUMN_ROLE_NAME, nullable = false)
  private String role;

  @Column(name = COLUMN_REFRESH_TOKEN_NAME, nullable = true)
  private String refreshToken;
}
