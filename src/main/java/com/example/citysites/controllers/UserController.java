package com.example.citysites.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/login")
    public String loginPage() {
        return "citysites/login";
    }

    @GetMapping("/register")
    public String registrationPage() {
        return "citysites/register";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "citysites/profile";
    }

    @GetMapping("/user/favorites")
    public String userFavoritesPage() {
        return "citysites/favorites";
    }
}
