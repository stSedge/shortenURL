package org.example.repository;

import java.util.Optional;
import java.util.UUID;
import org.example.database.HashTable;
import org.example.repository.dao.HashDao;


public class HashRepositoryImpl implements HashRepository{
    private static final HashTable dataBase = HashTable.getInstance();

    public HashRepositoryImpl() {
    }

    public String findHashByLongURL(String longURL) {
        return dataBase.getHash(longURL);
    }

    public String findHashByShortURL(String shortURL) {
        return dataBase.getLongURL(shortURL);
    }

    public String save(HashDao hashDao) {
        return dataBase.saveHash(new HashDao(hashDao.longURL(), hashDao.shortURL()));
    }

}
