package com.ayeganyan.currencytracker.model;

import io.swagger.annotations.ApiModelProperty;

public class JwtToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @ApiModelProperty(
            value = "JWT Token for use in Bearer authentication",
            example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTU5Mzk2NjkzNCwiaYNzIjoiQ3VycmVuY3kgVHJhY2tlciIsImV4cCI6MTU5Mzk4NDkzNH0.5_WJ0huP-o73WaooDdlNFeZOOFIFFNxDLLjEJI3eC9eWeSgyO944CB5Ly-4ag9-g9SbFHozmRIT4jlldMStMiw"
    )
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
