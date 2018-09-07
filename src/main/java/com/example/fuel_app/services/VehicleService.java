package com.example.fuel_app.services;

import com.example.fuel_app.entities.User;
import com.example.fuel_app.entities.Vehicle;
import com.example.fuel_app.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class VehicleService {
    @Autowired
    VehicleRepository vehicleRepository;

    public void saveVehicle(Vehicle vehicle){
        this.vehicleRepository.save(vehicle);
    }

    public Optional<Vehicle> findOneById(long id){
        return this.vehicleRepository.findById(id);
    }

    public Iterable<Vehicle> findAll(){
        return this.vehicleRepository.findAll();
    }

    public List<Vehicle> findAllByUserId(User user) {
        return this.vehicleRepository.findAllByUserIs(user);
    }
}
