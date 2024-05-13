package org.example.service;
import org.example.service.model.User;
import org.example.exception.EntityNotFoundException;

import java.sql.SQLException;


public interface UserService {
    long logIN(User var1);

    long signIN(User var1) throws EntityNotFoundException;
}
