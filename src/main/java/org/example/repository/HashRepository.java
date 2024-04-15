package main.java.org.example.repository;

import java.util.Optional;
import main.java.org.example.repository.dao.HashDao;

public interface HashRepository {
    Optional<String> findHashByLongURL(String LongURL);

    String save(HashDao var1);
}
