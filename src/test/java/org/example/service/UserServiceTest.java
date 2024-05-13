package org.example.service;

import org.example.jdbc.JdbcUtils;
import org.example.repository.UserRepositoryImpl;
import org.example.service.model.User;
import org.example.service.UserServiceImpl;
import org.example.service.UserService;
import org.example.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class UserServiceTest {
    boolean connection = JdbcUtils.createConnection();
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    @Test
    void testAddURL() throws EntityNotFoundException {
        //given:
        User hash = new User("angelina", "123");
        //when:
        userService.logIN(hash);
        userService.signIN(hash);
    }
}
