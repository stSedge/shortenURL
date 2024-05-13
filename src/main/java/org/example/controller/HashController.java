package org.example.controller;

import org.example.controller.dto.HashDto;
import org.example.service.HashService;
import org.example.service.model.Hash;
import org.example.exception.EntityNotFoundException;
import java.util.Optional;


public class HashController {
    private final HashService hashService;

    public HashController(HashService hashService) {
        this.hashService = hashService;
    }

    public String addHash(HashDto hashDto, long id) {
        return this.hashService.addHash((new Hash(hashDto.longURL())), id);
    }

    public String findHash(String shortURL) throws EntityNotFoundException {
        String url = this.hashService.findHashByShortURL(shortURL);
        if (url == null)
            throw new EntityNotFoundException("длинная ссылка не найдена");
        return url;
    }
}