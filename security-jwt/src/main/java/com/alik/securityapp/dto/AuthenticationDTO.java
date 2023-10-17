package com.alik.securityapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class AuthenticationDTO {
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2,max = 100,message = "Name should be between 2 and 100 characters")
    private String username;


    @NotEmpty(message = "Please enter your password")
    private String password;

    public AuthenticationDTO() {
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
