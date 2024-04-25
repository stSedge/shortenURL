package org.example.service.model;

public record Hash(String longURL, String shortURL) {

    public Hash(String longURL) {
        this(longURL, (String)null);
    }

    public Hash(String longURL, String shortURL) {
        this.longURL = longURL;
        this.shortURL = shortURL;
    }

    public String longURL() {
        return this.longURL;
    }

    public String shortURL() {
        return this.shortURL;
    }

}