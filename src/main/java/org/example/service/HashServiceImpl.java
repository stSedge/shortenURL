package main.java.org.example.service;

import java.util.Optional;
import java.util.Base64;
import java.util.Random;
import main.java.org.example.repository.HashRepository;
import main.java.org.example.repository.dao.HashDao;
import main.java.org.example.service.model.Hash;
import main.java.org.example.exception.EntityNotFoundException;


public class HashServiceImpl implements HashService{
    private final HashRepository hashRepository;

    public HashServiceImpl(HashRepository hashRepository) {
        this.hashRepository = hashRepository;
    }

    public String toShortURL(String longURL) {
        Random r = new Random();
        int cnt = r.nextInt(10, 60);
        int len = 7;
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

    public String addHash(Hash hash) {
        String val = findHashByLongURL(hash.longURL());
        if (val != null) {
            return val;
        }
        String shortURL = toShortURL(hash.longURL());
        while (this.hashRepository.findHashByShortURL(shortURL) != null) {
            shortURL = toShortURL(shortURL + hash.longURL());
        }
        HashDao hashDao = new HashDao(hash.longURL(), shortURL);
        return this.hashRepository.save(hashDao);
    }

    public String findHashByShortURL(String shortURL) throws EntityNotFoundException {
        return this.hashRepository.findHashByShortURL(shortURL);
    }

    public String findHashByLongURL(String longURL) {
        return this.hashRepository.findHashByLongURL(longURL);
    }
}
