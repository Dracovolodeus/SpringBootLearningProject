package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dto.converter.UserConverter;
import org.example.dto.model.user.UserDto;
import org.example.dto.model.user.UserRegisterDto;
import org.example.dto.model.user.UserUpdateDto;
import org.example.entity.UserEntity;
import org.example.exception.InvalidArgumentException;
import org.example.exception.NameOccupiedException;
import org.example.exception.NotFoundException;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private PasswordEncoder encoder;

    @Override
    @Transactional
    public UserDto registerUser(UserRegisterDto user) throws NameOccupiedException {
        if (userRepository.existsByName(user.getName())) {
            throw new NameOccupiedException("User with name " + user.getName() + " exists.");
        }
        UserEntity userEntity = UserEntity.builder()
                .name(user.getName())
                .password(encoder.encode(user.getPassword()))
                .role("user")
                .build();
        userRepository.save(userEntity);
        return UserConverter.toDto(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getInfo(long id) throws NotFoundException {
        return UserConverter.toDto(userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id " + id + " not found.")));
    }

    @Override
    @Transactional
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
