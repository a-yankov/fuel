package com.example.fuel_app.controllers;

import com.example.fuel_app.entities.User;
import com.example.fuel_app.models.RegistrationModel;
import com.example.fuel_app.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AdminController {
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/admin/register")
    String getRegister(@ModelAttribute RegistrationModel registrationModel){
        return "admin/register-page";
    }

    @PostMapping("/admin/register")
    String postRegister(@Valid @ModelAttribute RegistrationModel registrationModel, BindingResult bindingResult){
        System.out.println(bindingResult.hasErrors());
        this.userService.register(registrationModel);
        return "admin/register-page";
    }

    @GetMapping("/admin/users")
    public String getUsersPage(){
        return "admin/users";
    }

    @PostMapping("/admin/users")
    public String postUsersPage(Model model, Error error){
        model.addAttribute("users", this.userService.getAllUsers());
        return "admin/users";
    }

    @GetMapping("/admin/user/{id}")
    public String getUserPage(@PathVariable("id") int id, Model model){
        model.addAttribute("user", this.userService.findUserById(id));
        return "admin/user";
    }

    @PostMapping("/admin/user/{id}")
    public String updateUser(@PathVariable("id") int id, @ModelAttribute User user){
        userService.findUserById(id);
        System.out.println(user.isAccountNonExpired());
        return "admin/user";
    }
}
