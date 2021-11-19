package com.example.citysites.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
    @GetMapping("/login")
    public String showLoginForm(){return "citysites/login";}

    @GetMapping("/profile")
    public String profile(){

        return "citysites/profile";}
}
