package org.example.service.intr;

import org.example.dto.model.program.UserDto;
import org.example.exception.NotFoundException;

public interface UserServiceIntr {
    public UserDto getInfo(long id) throws NotFoundException;
}
