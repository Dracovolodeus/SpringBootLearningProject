package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.entity.UserEntity;
import org.example.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntity = userRepository.findByName(username);
        if (userEntity.isEmpty()) {
            throw new UsernameNotFoundException("User with name " + username + " not found.");
        }
        return new org.example.dto.model.user.UserDetails(userEntity.get());
    }
}
