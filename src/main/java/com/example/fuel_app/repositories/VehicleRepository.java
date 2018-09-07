package com.example.fuel_app.repositories;

import com.example.fuel_app.entities.User;
import com.example.fuel_app.entities.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {
    List<Vehicle> findAllByUserIs(User user);
}
