package com.example.citysites.controllers;

import com.example.citysites.models.Activity;
import com.example.citysites.repositories.ActivityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/activity/{id}")
    @ResponseBody
    public Activity singleActivityJSON(@PathVariable long id) {
        return activityDao.getById(id);
    }

    @GetMapping("/home")
    public String homePage(@RequestParam(name =  "userInput", defaultValue = "N/A",required = false) String userInput, Model model) {
        model.addAttribute("activities", activityDao.findAll());
        if (!userInput.equals("N/A")) {
            model.addAttribute("userInput", userInput);
        }
        return "citysites/home";
    }

    @PostMapping("/search")
    public String homePagePost(@RequestParam(name =  "userInput", defaultValue = "N/A",required = false) String userInput) {
        System.out.println(userInput);

        return "redirect:/home?userInput=" + userInput;
    }
}
