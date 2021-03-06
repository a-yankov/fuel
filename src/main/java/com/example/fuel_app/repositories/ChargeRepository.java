package com.example.fuel_app.repositories;

import com.example.fuel_app.entities.Charge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargeRepository extends CrudRepository<Charge, Long>{

}
