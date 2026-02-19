package org.example.dto.converter;

import lombok.experimental.UtilityClass;
import org.example.dto.model.user.UserDto;
import org.example.entity.UserEntity;

@UtilityClass
public class UserConverter {
  public UserDto toDto(UserEntity userEntity) {
    return UserDto.builder()
        .id(userEntity.getId())
        .name(userEntity.getName())
        .role(userEntity.getRole())
        .refreshToken(userEntity.getRefreshToken())
        .password(userEntity.getPassword())
        .build();
  }

  public UserEntity toEntity(UserDto userDto) {
    return UserEntity.builder()
        .id(userDto.getId())
        .name(userDto.getName())
        .role(userDto.getRole())
        .refreshToken(userDto.getRefreshToken())
        .password(userDto.getPassword())
        .build();
  }
}
