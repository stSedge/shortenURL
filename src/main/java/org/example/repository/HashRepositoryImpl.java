package main.java.org.example.repository;

import java.util.Optional;
import java.util.UUID;
import main.java.org.example.database.HashTable;
import main.java.org.example.repository.dao.HashDao;


public class HashRepositoryImpl implements HashRepository{
    private static final HashTable dataBase = HashTable.getInstance();

    public HashRepositoryImpl() {
    }

    public Optional<String> findHashByLongURL(String longURL) {
        return Optional.ofNullable(dataBase.getHash(longURL));
    }

    public String save(HashDao hashDao) {
        return dataBase.saveHash(new HashDao(hashDao.longURL(), hashDao.shortURL()));
    }

}
