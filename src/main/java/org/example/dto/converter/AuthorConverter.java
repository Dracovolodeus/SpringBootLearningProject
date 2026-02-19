package org.example.dto.converter;

import lombok.experimental.UtilityClass;
import org.example.dto.model.author.AuthorDto;
import org.example.entity.AuthorEntity;

@UtilityClass
public class AuthorConverter {
  public AuthorDto toDto(AuthorEntity author) {
    return AuthorDto.builder().id(author.getId()).name(author.getName()).build();
  }

  public AuthorEntity toEntity(AuthorDto author) {
    return AuthorEntity.builder().id(author.getId()).name(author.getName()).build();
  }
}
