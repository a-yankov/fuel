package com.example.fuel_app.repositories;

import com.example.fuel_app.entities.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<UserRole, Integer> {

    UserRole findOneByAuthority(String authority);
}
