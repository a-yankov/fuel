package com.example.fuel_app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.fuel_app.entities.Charge;
import com.example.fuel_app.entities.Vehicle;
import com.example.fuel_app.services.VehicleService;

@Controller
public class TimeLineController {
    @Autowired
    VehicleService vehicleService;

    @GetMapping("/timeline/{id}")
    public String getTimeline(Model model, @PathVariable("id") Long id){

        Optional<Vehicle> vehicle = vehicleService.findOneById(id);

        if (vehicle.isPresent()){
            List<Charge> charges = vehicle.get().getCharges();

            model.addAttribute("charges", charges);
        }

        return "timeline-new";
    }

    @GetMapping("/")
    public String getTimeline(){
        return "timeline";
    }
}
