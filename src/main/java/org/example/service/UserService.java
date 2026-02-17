package org.example.service;

import org.example.dto.model.user.UserDto;
import org.example.exception.NotFoundException;

public interface UserService {
    public UserDto getInfo(long id) throws NotFoundException;
}
