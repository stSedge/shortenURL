package org.example.repository.dao;

public record UserDao(String login, String password) {

    public UserDao(String login, String password) {
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
