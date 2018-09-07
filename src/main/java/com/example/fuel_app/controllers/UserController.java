package com.example.fuel_app.controllers;

import com.example.fuel_app.models.RegistrationModel;
import com.example.fuel_app.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl  userService;

    @GetMapping("/register")
    public String getRegister(@ModelAttribute RegistrationModel registrationModel){
        return "register";
    }

    @PostMapping("/register")
    String postRegister(@Valid @ModelAttribute RegistrationModel registrationModel, BindingResult bindingResult){
        System.out.println(bindingResult.hasErrors());
        this.userService.register(registrationModel);
        return "redirect:/register";
    }


    @GetMapping("/login")
    String getLogin(@RequestParam(required = false) String error, Model model){
        if (error != null){
            model.addAttribute("error", "username or password is incorrect.");
        }
        return "login";
    }
}
