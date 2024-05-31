package org.example.service;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Base64;
import java.util.Random;
import org.example.repository.HashRepository;
import org.example.repository.dao.HashDao;
import org.example.service.model.Hash;
import org.example.exception.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class HashServiceImpl implements HashService{
    private final HashRepository hashRepository;

    public HashServiceImpl(HashRepository hashRepository) {
        this.hashRepository = hashRepository;
    }

    public String toShortURL(String longURL) {
        Random r = new Random();
        int cnt = r.nextInt(10, 60);
        int len = 5;
        String shortURL = longURL;
        for (int i = 0; i < cnt; ++i) {
            while (shortURL.length() < 3 * len)
                shortURL += longURL;
            shortURL = Base64.getEncoder().encodeToString((shortURL.getBytes()));
            shortURL = shortURL.substring(len);
        }
        shortURL = shortURL.substring(0, len);
        return shortURL;
    }

    @Override
    public String addHash(Hash hash, long id) {
        String val = findHashByLongURL(hash.longURL());
        if (val != null) {
            return val;
        }
        String shortURL = toShortURL(hash.longURL());
        try {
            while (this.hashRepository.findHashByShortURL(shortURL) != null) {
                shortURL = toShortURL(shortURL + hash.longURL());
            }
            HashDao hashDao = new HashDao(hash.longURL(), shortURL);
            return this.hashRepository.save(hashDao, id);
        }
        catch (Exception ex) {
           throw new RuntimeException("Error occurred while adding to DB", ex);
        }
    }

    @Override
    public String findHashByShortURL(String shortURL) throws EntityNotFoundException {
        try {
            return this.hashRepository.findHashByShortURL(shortURL);
        }
        catch (SQLException ex) {
            throw new RuntimeException("Error occurred while finding longurl in DB", ex);
        }
    }

    @Override
    public String findHashByLongURL(String longURL) {
        try {
            return this.hashRepository.findHashByLongURL(longURL);
        }
        catch (SQLException ex) {
            throw new RuntimeException("Error occurred while finding shorturl in  DB", ex);
        }
    }
}
