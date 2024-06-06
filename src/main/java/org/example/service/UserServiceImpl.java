package org.example.service;

import java.sql.SQLException;

import org.example.dao.entity.UserEntity;
import org.example.dao.repository.UserRepository;
import org.example.exception.EntityNotFoundException;
import org.example.service.model.User;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public long signIN(User user) {
        try {
            UserEntity userDao = new UserEntity(user.login(), user.password());
            return this.userRepository.findUser(userDao.login(), userDao.password());
        } catch (Exception ex) {
            throw new RuntimeException("Error occurred while finding user in DB", ex);
        }
    }

    @Override
    public long logIN(User user) {
        try {
            UserEntity userDao = new UserEntity(user.login(), user.password());
            try {
                this.userRepository.findUser(userDao.login());
            }
            catch (Exception e) {
                this.userRepository.saveUser(userDao.login(), userDao.password());
                return this.userRepository.findUser(userDao.login());
            }
            return -1;
        } catch (SQLException ex) {
            throw new RuntimeException("Error occurred while adding user to DB", ex);
        }
    }
}
