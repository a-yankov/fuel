package com.example.fuel_app.services;

import com.example.fuel_app.entities.UserRole;
import com.example.fuel_app.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl {
    @Autowired
    RoleRepository roleRepository;

    public UserRole findRoleByName(String name){
        return this.roleRepository.findOneByAuthority(name);
    }
}
