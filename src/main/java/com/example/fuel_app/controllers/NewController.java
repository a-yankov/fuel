package com.example.fuel_app.controllers;

import com.example.fuel_app.entities.Charge;
import com.example.fuel_app.entities.User;
import com.example.fuel_app.entities.Vehicle;
import com.example.fuel_app.services.ChargeService;
import com.example.fuel_app.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.*;

@Controller
public class NewController {

    @Autowired
    ChargeService chargeService;
    @Autowired
    VehicleService vehicleService;

    @GetMapping("/new-charge")
    public String getHome(Model model){
        Charge charge = new Charge();

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Vehicle> vehicles =  vehicleService.findAllByUserId(user);

        if (vehicles.size() == 0){
            model.addAttribute("error", "Create a vehicle");
        }else {
            Optional<Vehicle> vehicle = Optional.ofNullable(vehicles.get(0));


            if (vehicle.isPresent()) {
                charge.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
                model.addAttribute("vehicle", vehicle.get());
                model.addAttribute("vehicles", vehicles);
                model.addAttribute("charge", charge);
            }
        }

        return "new-charge";
    }

    @PostMapping(path ="/new-charge")
    public String postHome(@ModelAttribute("charge") Charge charge, @ModelAttribute("vehicle") Vehicle modelVehicle, @RequestParam(value = "vehicle_id", required = false) Long id){
        String debug=  "asd";
        Optional<Vehicle> vehicle;


        if (id != null){
             vehicle = this.vehicleService.findOneById(id);
        }else{
            vehicle = this.vehicleService.findOneById(modelVehicle.getId());
        }
        if (vehicle.isPresent()){
            vehicle.get().setOdometer(modelVehicle.getOdometer());
            vehicle.get().getCharges().add(charge);
            charge.setVehicle(vehicle.get());
            this.chargeService.saveCharge(charge);
    }
        return "redirect:/new-charge";
    }
}
