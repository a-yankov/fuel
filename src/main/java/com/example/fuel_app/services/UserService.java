package com.example.fuel_app.services;

import com.example.fuel_app.models.RegistrationModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void register(RegistrationModel registrationModel);
}
