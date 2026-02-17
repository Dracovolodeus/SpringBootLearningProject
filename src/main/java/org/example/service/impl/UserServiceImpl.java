package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dto.converter.UserConverter;
import org.example.dto.model.user.UserDto;
import org.example.dto.model.user.UserUpdateDto;
import org.example.entity.UserEntity;
import org.example.exception.InvalidArgumentException;
import org.example.exception.NotFoundException;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public UserDto getInfo(long id) throws NotFoundException {
        return UserConverter.toDto(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id " + id + " not found.")));
    }

    @Override
    public UserDto update(UserUpdateDto userUpdateDto) throws NotFoundException, InvalidArgumentException {
        UserEntity userEntity = userRepository
                .findById(userUpdateDto.getId())
                .orElseThrow(
                        () -> new NotFoundException("User with id " + userUpdateDto.getId() + " not found")
                );
        userEntity.setName(userUpdateDto.getName());
        userEntity.setRole(userUpdateDto.getRole());
        return UserConverter.toDto(userEntity);
    }
}
