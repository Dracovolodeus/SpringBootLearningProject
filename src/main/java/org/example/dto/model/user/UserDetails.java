package org.example.dto.model.user;

import java.util.Collection;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.example.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@AllArgsConstructor
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

  private UserEntity userEntity;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority(userEntity.getRole()));
  }

  @Override
  public String getPassword() {
    return userEntity.getPassword();
  }

  @Override
  public String getUsername() {
    return userEntity.getName();
  }
}
