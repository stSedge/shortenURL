package org.example.dao.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "users")
@Table(name = "USERS")
public class UserEntity {
    @Id
    @Column(name = "LOGIN")
    String login;
    @Column(name = "PASSWORD")
    String password;

    public UserEntity() {}

    public UserEntity(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String login() {
        return login;
    }

    public String password() {
        return password;
    }
}
