package com.example.citysites.controllers;

import com.example.citysites.repositories.ActivityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    ActivityRepository activityDao;

    public WelcomeController(ActivityRepository activityDao) {
        this.activityDao = activityDao;
    }

    @GetMapping("/welcome")
    public String welcomePage(Model model) {
        model.addAttribute("activities", activityDao.findAll());
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
