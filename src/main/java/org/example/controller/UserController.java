package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.model.program.UserDto;
import org.example.service.intr.UserServiceIntr;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    private UserServiceIntr userService;

    @GetMapping("/{id}/")
    public UserDto getInfo(@PathVariable long id) {
        return userService.getInfo(id);
    }

}
