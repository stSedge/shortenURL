package org.example.service;

import org.example.repository.HashRepositoryImpl;
import org.example.service.model.Hash;
import org.example.service.HashServiceImpl;
import org.example.service.HashService;
import org.example.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HashServiceTest {
    private final HashService hashService = new HashServiceImpl(new HashRepositoryImpl());
    @Test
    void testAddURL() throws EntityNotFoundException {
        //given:
        Hash hash = new Hash("tinkoff");
        //when:
        String shortURL = hashService.addHash(hash);
        String savedHash = hashService.findHashByLongURL(hash.longURL());
        String longURL = hashService.findHashByShortURL(shortURL);
        //then:
        assertEquals(hash.shortURL(), savedHash);
        assertEquals(hash.longURL(), longURL);
    }
}
