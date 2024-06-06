package org.example.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "hashes")
@Table(name = "URLS")
public class HashEntity {
    @Id
    @Column(name = "LONGURL")
    private String longURL;
    @Column(name = "SHORTURL")
    private String shortURL;

    public HashEntity() {
    }
    public HashEntity(String longURL) {
        this(longURL, (String)null);
    }

    public HashEntity(String longURL, String shortURL) {
        this.longURL = longURL;
        this.shortURL = shortURL;
    }

    public String longURL() {
        return longURL;
    }

    public String shortURL() {
        return shortURL;
    }
}
