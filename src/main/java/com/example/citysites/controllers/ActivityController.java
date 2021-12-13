package com.example.citysites.controllers;

import com.example.citysites.models.Activity;
import com.example.citysites.models.ActivityImage;
import com.example.citysites.models.User;
import com.example.citysites.repositories.ActivityRepository;
import com.example.citysites.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/activity/{id}")
    public String singleActivityDetails(@PathVariable long id, Model model, Principal principal) {
        if (principal != null) {
            User user = userDao.findByUsername(principal.getName());
            model.addAttribute("favorites", user.getFavoriteActivities());
        }

        Activity activity = activityDao.getById(id);
        List<ActivityImage> images = activity.getActivityImages();
        model.addAttribute("activity", activity);
        model.addAttribute("images", images);
        return "citysites/details";
    }


    @PostMapping("activity/{id}")
    public String activityDetailPage(@ModelAttribute Activity activity, @RequestParam List<String> urls) {
        List<ActivityImage> images = activity.getActivityImages();
        System.out.println(images);

        if (images == null) {
            images = new ArrayList<>();
        }

        for (String url : urls) {
            ActivityImage activityImage = new ActivityImage(url);
            activityImage.setActivity(activity);
            images.add(activityImage);
        }

        Activity editedActivity = activityDao.getById(activity.getId());

        editedActivity.setName(activity.getName());
        editedActivity.setDescription(activity.getDescription());
        editedActivity.setHours(activity.getHours());
        editedActivity.setActivityImages(images);

        activityDao.save(editedActivity);
        return "redirect:/activity/" + activity.getId();
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

    @PostMapping("/activity/{id}/delete")
    public String deleteActivity(@PathVariable long id){
//        Activity activity = activityDao.getById(id);
//        List<User> userList = new ArrayList<>();
//        activity.setUserFavorites(userList);
//        activityDao.save(activity);
        activityDao.deleteById(id);
        return "redirect:/home";
    }
}
