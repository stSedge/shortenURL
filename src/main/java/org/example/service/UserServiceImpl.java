package org.example.service;

import java.sql.SQLException;
import org.example.repository.UserRepository;
import org.example.repository.dao.UserDao;
import org.example.service.model.User;
import org.example.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public long signIN(User user) {
        try {
            UserDao userDao = new UserDao(user.login(), user.password());
            return this.userRepository.findUser(userDao);
        }
        catch (Exception ex) {
            throw new RuntimeException("Error occurred while finding user in DB", ex);
        }
    }

    @Override
    public long logIN(User user) {
        try {
            UserDao userDao = new UserDao(user.login(), user.password());
            return this.userRepository.saveUser(userDao);
        }
        catch (SQLException ex) {
            throw new RuntimeException("Error occurred while adding user to DB", ex);
        }
    }

}
