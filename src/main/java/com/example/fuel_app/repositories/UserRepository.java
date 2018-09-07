package com.example.fuel_app.repositories;

import com.example.fuel_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findOneByUsername(String username);

    User findById(int id);


}
