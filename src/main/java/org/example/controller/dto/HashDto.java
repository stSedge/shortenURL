package main.java.org.example.controller.dto;

public record HashDto(String longURL, String shortURL) {

    public HashDto(String longURL) {
        this(longURL, (String)null);
    }

    public HashDto(String longURL, String shortURL) {
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