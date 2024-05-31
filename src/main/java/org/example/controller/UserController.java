package org.example.controller;

import org.example.controller.dto.UserDto;
import org.example.service.UserService;
import org.example.service.model.User;
import org.example.exception.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/create_acc")
    public long logIN(@RequestBody UserDto userDto) {
        return this.userService.logIN(new User(userDto.login(), userDto.password()));
    }

    @GetMapping("/{id}")
    public long signIN(@PathVariable("id") UserDto userDto) throws EntityNotFoundException {
        long id = this.userService.signIN(new User(userDto.login(), userDto.password()));
        if (id == -1)
            throw new EntityNotFoundException("такой пользователь не найден");
        return id;
    }
}