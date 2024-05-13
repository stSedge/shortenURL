package org.example.repository;

import org.example.jdbc.JdbcUtils;
import java.sql.SQLException;

import org.example.repository.dao.HashDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class HashRepositoryImpl implements HashRepository{

    public HashRepositoryImpl() {
    }

    public String findHashByLongURL(String longURL) throws SQLException {
        String sql = "SELECT SHORTURL FROM URLS WHERE LONGURL =?";
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, longURL);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
            return resultSet.getString("SHORTURL");
        return null;
    }

    public long findURLID(HashDao hashDao) throws SQLException {
        String sql = "SELECT * FROM URLS WHERE LONGURL =?";
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, hashDao.longURL());
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
            return resultSet.getLong("ID");
        return -1;
    }

    public String findHashByShortURL(String shortURL) throws SQLException {
        String sql = "SELECT LONGURL FROM URLS WHERE SHORTURL =?";
        Connection connection = JdbcUtils.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, shortURL);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
            return resultSet.getString("LONGURL");
        return null;
    }

    public String save(HashDao hashDao, long id) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String sql = "INSERT INTO URLS (LONGURL, SHORTURL) VALUES (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, hashDao.longURL());
        preparedStatement.setString(2, hashDao.shortURL());
        preparedStatement.execute();
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
             preparedStatement) {
            if (generatedKeys.next()) {
                sql = "INSERT INTO QUERIES (USERID, URLID) VALUES (?,?)";
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement1.setLong(1, id);
                preparedStatement1.setLong(2, findURLID(hashDao));
                preparedStatement1.execute();
                try (ResultSet generatedKeys1 = preparedStatement1.getGeneratedKeys();
                     preparedStatement1) {
                    if (generatedKeys1.next()) {
                        return hashDao.shortURL();
                    }
                }
            }
        }
        preparedStatement.close();
        return null;
        //return dataBase.saveHash(new HashDao(hashDao.longURL(), hashDao.shortURL()));
    }

}
