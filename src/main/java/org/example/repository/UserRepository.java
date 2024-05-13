package org.example.repository;

import java.sql.SQLException;
import org.example.repository.dao.UserDao;

public interface UserRepository {

    long saveUser(UserDao var1) throws SQLException;

    long findUser(UserDao var1) throws SQLException;
}
