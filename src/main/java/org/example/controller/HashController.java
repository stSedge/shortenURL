package main.java.org.example.controller;

import main.java.org.example.controller.dto.HashDto;
import main.java.org.example.service.HashService;
import main.java.org.example.service.model.Hash;

import java.util.Optional;
//import org.example.exception.EntityNotFoundException;

public class HashController {
    private final HashService hashService;

    public HashController(HashService hashService) {
        this.hashService = hashService;
    }

    public String addHash(HashDto hashDto) {
        return this.hashService.addHash(new Hash(hashDto.longURL()));
    }

    public String findHash(String shortURL) {
        return this.hashService.findHashByShortURL(shortURL);
    }

    /*public HashDto getHash(String longURL) throws EntityNotFoundException {
        Hash hash = this.hashService.findHash(id);
        return new HashDto();
    }*/
}