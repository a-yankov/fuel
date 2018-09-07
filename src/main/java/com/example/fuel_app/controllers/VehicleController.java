package com.example.fuel_app.controllers;

import com.example.fuel_app.entities.User;
import com.example.fuel_app.entities.Vehicle;
import com.example.fuel_app.services.UserServiceImpl;
import com.example.fuel_app.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.sql.Date;
import java.util.Calendar;


@Controller
public class VehicleController {
    @Autowired
    VehicleService vehicleService;
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/vehicle/new")
    public String getNewVehicle(Model model, Principal principal){
        System.out.println(principal.getName());
        model.addAttribute("vehicle", new Vehicle());
        return "new-vehicle";
    }

    @PostMapping("/vehicle/new")
    public String postNewVehicle(@ModelAttribute("vehicle") Vehicle vehicle, Principal principal){
        User user = this.userService.loadUserByUsername(principal.getName());

        vehicle.setCreationDate(new Date(Calendar.getInstance().getTimeInMillis()));
        //sets odometer like start odometer
        vehicle.setOdometer(vehicle.getStartOdometer());
        vehicle.setUser(user);
        this.vehicleService.saveVehicle(vehicle);
        System.out.println("fuel type" + vehicle.getFuelType());
        return "redirect:/vehicle/new";
    }

    @GetMapping("/my-vehicles")
    public String getMyVehicles(Model model, Principal principal){
        User user = this.userService.loadUserByUsername(principal.getName());

        model.addAttribute("vehicles", user.getVehicles());

        return "my-vehicles";
    }
}
