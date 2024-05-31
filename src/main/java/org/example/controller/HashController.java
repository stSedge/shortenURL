package org.example.controller;

import org.example.controller.dto.HashDto;
import org.example.service.HashService;
import org.example.service.model.Hash;
import org.example.exception.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/hash")
public class HashController {
    private final HashService hashService;

    public HashController(HashService hashService) {
        this.hashService = hashService;
    }

    @PostMapping(value = "/create")
    public String addHash(@RequestBody HashDto hashDto, long id) {
        return this.hashService.addHash((new Hash(hashDto.longURL())), id);
    }

    @GetMapping("/{shortUrl}")
    public String findHash(@PathVariable("shortUrl") String shortURL) throws EntityNotFoundException {
        String url = this.hashService.findHashByShortURL(shortURL);
        if (url == null)
            throw new EntityNotFoundException("длинная ссылка не найдена");
        return url;
    }
}