//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package main.java.org.example.service;

import main.java.org.example.service.model.Hash;

import java.util.Optional;

public interface HashService {
    String addHash(Hash var1);

    Optional<String> findHash(String var1);
}
