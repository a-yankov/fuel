package com.example.fuel_app.controllers;

import com.example.fuel_app.entities.Charge;
import com.example.fuel_app.entities.User;
import com.example.fuel_app.entities.Vehicle;
import com.example.fuel_app.services.ChargeService;
import com.example.fuel_app.services.UserServiceImpl;
import com.example.fuel_app.services.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.*;

@Controller
public class StatisticsController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    ChargeService chargeService;
    @Autowired
    VehicleService vehicleService;

    @GetMapping("/statistics/{id}")
    public String getStatistics(Principal principal, Model model, @PathVariable("id") Long id) {
        User user = userService.loadUserByUsername(principal.getName());


        Optional<Vehicle> vehicle = vehicleService.findOneById(id);


        if (vehicle.isPresent()) {

            List<Charge> charges = vehicle.get().getCharges();


            ArrayList<Charge> chargesList = new ArrayList<>();
            chargesList.addAll(charges);

            Collections.sort(chargesList);

            double consumption;
            int startOdo = chargesList.get(0).getTripMeter();
            int endOdo = chargesList.get(chargesList.size() - 1).getTripMeter();
            int dist = endOdo - startOdo;
            double litters = 0.0;
            for (int i = 0; i < chargesList.size() - 1; i++) {
                litters += chargesList.get(i).getQuantity();
            }

            double littersPer100 = litters / (dist / 100);

            int fillUpsYearToDateCharges = charges.size();
            int totalLitters = 0;
            double totalAmount = 0;
            int totalDistance;
            for (Charge charge : charges) {
                totalLitters += charge.getQuantity();
                totalAmount += charge.getQuantity() * charge.getFuelPrice();
            }
            model.addAttribute("totalLitters", totalLitters);
            model.addAttribute("totalAmount", totalAmount);
            model.addAttribute("fillUpsYearToDateCharges", fillUpsYearToDateCharges);
            model.addAttribute("YTDConsumption", littersPer100);


//            System.out.println(Collections.);
        }

        return "statistics";
    }
}
