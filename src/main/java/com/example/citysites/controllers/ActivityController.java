package com.example.citysites.controllers;

import com.example.citysites.models.Activity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActivityController {
    @GetMapping("/activity/details")
    public String singleActivityDetails() {
        return "citysites/details";
    }

    @GetMapping("/activity/create")
    public String activityCreationPage(Model model) {
        model.addAttribute("activity", new Activity());
        return "citysites/add-activity";
    }

//    PostMapping to add new Activity to DB
}
