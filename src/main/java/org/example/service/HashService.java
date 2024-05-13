//
package org.example.service;

import org.example.service.model.Hash;
import org.example.exception.EntityNotFoundException;

import java.util.Optional;

public interface HashService {
    String addHash(Hash var1, long id) ;

    String findHashByShortURL(String var1) throws EntityNotFoundException;

    public String findHashByLongURL(String var2);
}
