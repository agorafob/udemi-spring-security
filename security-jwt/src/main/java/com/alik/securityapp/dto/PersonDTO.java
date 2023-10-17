package com.alik.securityapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PersonDTO {
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2,max = 100,message = "Name should be between 2 and 100 characters")
    private String username;

    @Min(value = 1900,message = "Year should be more than 1900")
    private int year_of_birth;

    @NotEmpty(message = "Please enter your password")
    private String password;

    public PersonDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getYear_of_birth() {
        return year_of_birth;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
