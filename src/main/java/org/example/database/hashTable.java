package database;

import java.util.HashMap;

public class hashTable {
    private final HashMap<String, String> hashTable;
    private static hashTable instance;
    private hashTable() {hashTable = new HashMap<>(); }

    public static synchronized hashTable getInstance() {
        if (instance == null) {
            instance = new hashTable();
        }
        return instance;
    }

    public String saveHash(String longUrl) {
        String shortUrl = "";
        getInstance().hashTable.put(longUrl, shortUrl);
        return shortUrl;
    }

    public String getHash(String longUrl) {
        return getInstance().hashTable.get(longUrl);
    }

}
