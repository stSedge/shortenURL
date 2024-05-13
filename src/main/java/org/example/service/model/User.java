package org.example.service.model;

public record User(String login, String password) {

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String login() {
        return this.login;
    }

    public String password() {
        return this.password;
    }

}