package main.java.org.example.controller;

import main.java.org.example.controller.dto.HashDto;
import main.java.org.example.service.HashService;
import main.java.org.example.service.model.Hash;
import main.java.org.example.exception.EntityNotFoundException;
import java.util.Optional;


public class HashController {
    private final HashService hashService;

    public HashController(HashService hashService) {
        this.hashService = hashService;
    }

    public String addHash(HashDto hashDto) {
        return this.hashService.addHash(new Hash(hashDto.longURL()));
    }

    public String findHash(String shortURL) throws EntityNotFoundException {
        String url = this.hashService.findHashByShortURL(shortURL);
        if (url == null)
            throw new EntityNotFoundException("длинная сслыка не найдена");
        return url;
    }

    /*public HashDto getHash(String longURL) throws EntityNotFoundException {
        Hash hash = this.hashService.findHash(id);
        return new HashDto();
    }*/
}