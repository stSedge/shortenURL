package main.repository;

import java.util.Optional;

public interface HashRepository {
    Optional<String> findHashByShortURL(String shortUrl);

    String save(String longURL);

}
