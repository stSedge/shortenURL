package org.example.repository;

import org.example.jdbc.JdbcUtils;
import java.sql.SQLException;

import org.example.repository.UserRepository;
import org.example.repository.dao.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserRepositoryImpl implements UserRepository {

    public UserRepositoryImpl() {
    }

    public long findUser(UserDao userDao) throws SQLException {
        String sql = "SELECT ID FROM USERS WHERE LOGIN =? AND PASSWORD=?";
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userDao.login());
        statement.setString(2, userDao.password());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
            return resultSet.getLong("ID");
        return -1;
    }

    public long saveUser(UserDao userDao) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String sql = "INSERT INTO USERS (LOGIN, PASSWORD) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, userDao.login());
        preparedStatement.setString(2, userDao.password());
        preparedStatement.execute();
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
             preparedStatement) {
            if (generatedKeys.next()) {
                return findUser(userDao);
            }
        }
        preparedStatement.close();
        return -1;
    }
}