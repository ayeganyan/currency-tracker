package com.ayeganyan.currencytracker.model;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Credential {

    public static Credential from(CredentialEntity credentialEntity) {
        return new Credential(credentialEntity.getUsername(), credentialEntity.getPassword());
    }

    @NotEmpty(message = "Please provide username")
    @Size(min = 3, message = "username should be at least 3 characters long")
    @ApiModelProperty(name = "User unique identifier", required = true)
    private String username;

    @NotEmpty(message = "Please provide password")
    @Size(min = 5, message = "Password should be at least 5 characters long")
    @ApiModelProperty(notes = "User's password", required = true, hidden = true)
    private String password;

    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Credential() {
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
