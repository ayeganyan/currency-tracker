package com.ayeganyan.currencytracker.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {

    public static User from(UserEntity userEntity) {
        return new User(userEntity.getUsername(), userEntity.getPassword());
    }

    @NotNull
    @Size(min = 3)
    private String username;

    @Size(min = 5)
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
