package com.ayeganyan.currencytracker.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {

    public static User from(UserEntity userEntity) {
        return new User(userEntity.getUsername(), userEntity.getPassword());
    }

    @NotEmpty(message = "Please provide username")
    @Size(min = 3, message = "username should be at least 3 characters long")
    @ApiModelProperty(notes = "User unique identifier", required = true, name = "Username")
    private String username;

    @NotEmpty(message = "Please provide password")
    @Size(min = 5, message = "Password should be at least 5 characters long")
    @ApiModelProperty(notes = "User's password", required = true, name = "Password", hidden = true)
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
