package com.example.fuel_app.models;

import javax.validation.constraints.Size;

public class RegistrationModel {
    @Size(min = 4)
    String username;
    String email;
    @Size(min = 4)
    String password;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
