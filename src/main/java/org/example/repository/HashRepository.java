package org.example.repository;

import java.sql.SQLException;
import java.util.Optional;
import org.example.repository.dao.HashDao;

public interface HashRepository {
    String findHashByLongURL(String LongURL) throws SQLException;

    String findHashByShortURL(String ShortURL) throws SQLException;

    String save(HashDao var1, long id) throws SQLException;
}
