package com.example.citysites.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @GetMapping("/welcome")
    public String welcomePage() {
        return "citysites/welcome";
    }

    @GetMapping("/")
    public String redirectToWelcome() {
        return "redirect:/welcome";
    }

    @GetMapping("/about-us")
    public String aboutUs() {
        return "citysites/about-us";
    }
}
