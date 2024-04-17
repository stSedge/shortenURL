package main.java.org.example.repository;

import java.util.Optional;
import main.java.org.example.repository.dao.HashDao;

public interface HashRepository {
    String findHashByLongURL(String LongURL);

    String findHashByShortURL(String ShortURL);

    String save(HashDao var1);
}
