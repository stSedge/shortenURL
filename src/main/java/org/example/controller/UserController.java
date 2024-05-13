package org.example.controller;

import org.example.controller.dto.UserDto;
import org.example.service.UserService;
import org.example.service.model.User;
import org.example.exception.EntityNotFoundException;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public long logIN(UserDto userDto) {
        return this.userService.logIN(new User(userDto.login(), userDto.password()));
    }

    public long signIN(UserDto userDto) throws EntityNotFoundException {
        long id = this.userService.signIN(new User(userDto.login(), userDto.password()));
        if (id == -1)
            throw new EntityNotFoundException("такой пользователь не найден");
        return id;
    }
}