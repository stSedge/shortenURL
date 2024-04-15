package main.java.org.example.service;

import java.util.Optional;
import main.java.org.example.repository.HashRepository;
import main.java.org.example.repository.dao.HashDao;
import main.java.org.example.service.model.Hash;

public class HashServiceImpl implements HashService{
    private final HashRepository hashRepository;

    public HashServiceImpl(HashRepository hashRepository) {
        this.hashRepository = hashRepository;
    }

    public String addHash(Hash hash) {
        HashDao hashDao = new HashDao(hash.longURL());
        return this.hashRepository.save(hashDao);
    }

    public Optional<String> findHash(String longURL) {
        Optional<String> shortURL = this.hashRepository.findHashByLongURL(longURL);
        return shortURL;

    }
}
