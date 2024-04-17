package main.java.org.example.database;

import java.util.HashMap;
import java.util.Objects;

import main.java.org.example.repository.dao.HashDao;

public class HashTable {
    private final HashMap<String, String> hashTable = new HashMap<>();
    private static HashTable instance;
    private HashTable() { }

    public static synchronized HashTable getInstance() {
        if (instance == null) {
            instance = new HashTable();
        }
        return instance;
    }

    public String saveHash(HashDao hashDao) {
        getInstance().hashTable.put(hashDao.longURL(), hashDao.shortURL());
        return hashDao.shortURL();
    }

    public String getHash(String longURL) {
        return getInstance().hashTable.get(longURL);
    }

    public String getLongURL(String shortURL) {

        for (String entry : getInstance().hashTable.keySet()) {
            String value = getInstance().hashTable.get(entry);
            if (Objects.equals(value, shortURL))
                return entry;
        }
        return null;
    }

}
