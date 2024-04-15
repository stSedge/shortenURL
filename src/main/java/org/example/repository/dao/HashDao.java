package main.java.org.example.repository.dao;

public record HashDao(String longURL, String shortURL) {

    public HashDao(String longURL) {
        this(longURL, (String)null);
    }

    public HashDao(String longURL, String shortURL) {
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