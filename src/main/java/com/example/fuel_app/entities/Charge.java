package com.example.fuel_app.entities;


import com.example.fuel_app.entities.Vehicle;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
public class Charge  implements Comparable<Charge> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long chargeId;
    @NotNull
    private double quantity;
    @NotNull
    private double fuelPrice;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    private int tripMeter;

    @NotNull
    @ManyToOne
    @JoinColumn(name="vehicle_id")
    private Vehicle vehicle;



    public long getChargeId() {
        return chargeId;
    }

    public void setChargeId(long chargeId) {
        this.chargeId = chargeId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }



    public double getFuelPrice() {
        return fuelPrice;
    }

    public void setFuelPrice(double fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    public Date  getDate() {
        return date;
    }

    public void setDate(Date  date) {
        this.date = date;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getTripMeter() {
        return tripMeter;
    }

    public void setTripMeter(int tripMeter) {
        this.tripMeter = tripMeter;
    }

    @Override
    public int compareTo(Charge charge) {
        return charge.getChargeId() >= chargeId ? -1 : 0;
    }
}
