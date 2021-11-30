package com.example.citysites.controllers;

import com.example.citysites.models.Activity;
import com.example.citysites.repositories.ActivityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {
    ActivityRepository activityDao;

    public HomeController(ActivityRepository activityDao) {
        this.activityDao = activityDao;
    }

    @GetMapping("/api/map")
    @ResponseBody
    public List<Activity> allActivitiesInJSON() {
        return activityDao.findAll();
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("activities", activityDao.findAll());
        return "citysites/home";
    }
}
