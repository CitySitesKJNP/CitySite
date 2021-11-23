package com.example.citysites.controllers;

import com.example.citysites.models.Activity;
import com.example.citysites.models.ActivityImage;
import com.example.citysites.models.User;
import com.example.citysites.repositories.ActivityRepository;
import com.example.citysites.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ActivityController {
    private UserRepository userDao;
    private ActivityRepository activityDao;

    public ActivityController(UserRepository userDao, ActivityRepository activityDao) {
        this.userDao = userDao;
        this.activityDao = activityDao;
    }

    @GetMapping("/activity/details")
    public String singleActivityDetails(Model model, Principal principal) {
        String un = principal.getName();
        model.addAttribute("user", userDao.findByUsername(un));

        return "citysites/details";
    }

    @GetMapping("/activity/create")
    public String activityCreationPage(Model model) {
        model.addAttribute("activity", new Activity());
        return "citysites/add-activity";
    }

    @PostMapping("/activity/create")
    public String createActivity(@ModelAttribute Activity activity, @RequestParam List<String> urls) {
        List<ActivityImage> images = new ArrayList<>();

        for (String url : urls) {
            ActivityImage activityImage = new ActivityImage(url);
            activityImage.setActivity(activity);
            images.add(activityImage);
        }

        activity.setActivityImages(images);

        activityDao.save(activity);
        return "redirect:/home";
    }

//    PostMapping to add new Activity to DB
}
