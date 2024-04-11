package main.repository;

import database.hashTable;

import java.util.Optional;


public class HashRepositorylmpl implements HashRepository{
    private static final hashTable dataBase = hashTable.getInstance();

    @Override
    public Optional<String> findHashByShortURL(String shortURL) {
        return Optional.ofNullable(dataBase.getHash(shortURL));
    }

    @Override
    public String save(String longURL) {
        String shortURL = longURL;
        if (shortURL != null) {
            dataBase.saveHash(longURL);
            return shortURL;
        }

        String hash = "result";
        dataBase.saveHash(hash);
        return hash;
    }

}
