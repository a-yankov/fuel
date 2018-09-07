package com.example.fuel_app.models;

import com.example.fuel_app.entities.Charge;
import com.example.fuel_app.entities.Vehicle;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


import java.sql.Date;

public class chargeModel {

    Vehicle vehicle;
    int odometer;
    int trip;
    double fuelPrice;
    @Positive
    double quantity;

    Date date;
}
