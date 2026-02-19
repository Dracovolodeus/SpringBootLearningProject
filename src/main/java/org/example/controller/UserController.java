package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dto.model.user.UserDto;
import org.example.dto.model.user.UserRegisterDto;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
  private UserService userService;

  @PostMapping("/register")
  public UserDto register(@RequestBody UserRegisterDto userRegisterDto) {
    return userService.registerUser(userRegisterDto);
  }

  @GetMapping("/{id}")
  public UserDto getInfo(@PathVariable long id) {
    return userService.getInfo(id);
  }
}
