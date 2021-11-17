package com.example.citysites.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActivityController {
    @GetMapping("/activity/details")
    public String singleActivityDetails() {
        return "citysites/details";
    }
}
