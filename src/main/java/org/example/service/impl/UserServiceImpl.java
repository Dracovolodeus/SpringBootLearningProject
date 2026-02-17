package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dto.converter.UserConverter;
import org.example.dto.model.program.UserDto;
import org.example.exception.NotFoundException;
import org.example.repository.UserRepository;
import org.example.service.intr.UserServiceIntr;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserServiceIntr {
    private UserRepository userRepository;

    @Override
    public UserDto getInfo(long id) throws NotFoundException {
        return UserConverter.toDto(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id " + id + " not found.")));
    }
}
