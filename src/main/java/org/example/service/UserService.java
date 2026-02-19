package org.example.service;

import org.example.dto.model.user.UserDto;
import org.example.dto.model.user.UserRegisterDto;
import org.example.dto.model.user.UserUpdateDto;
import org.example.exception.InvalidArgumentException;
import org.example.exception.NameOccupiedException;
import org.example.exception.NotFoundException;

public interface UserService {

  public UserDto registerUser(UserRegisterDto user) throws NameOccupiedException;

  public UserDto getInfo(long id) throws NotFoundException;

  public UserDto update(UserUpdateDto userUpdateDto)
      throws NotFoundException, InvalidArgumentException;
}
