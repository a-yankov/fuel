package com.example.fuel_app.services;

import com.example.fuel_app.entities.Charge;
import com.example.fuel_app.repositories.ChargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChargeService {
    @Autowired
    ChargeRepository chargeRepository;

    public void saveCharge(Charge charge){
        this.chargeRepository.save(charge);
    }
}

