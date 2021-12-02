package com.example.citysites.controllers;

import com.example.citysites.models.Activity;
import com.example.citysites.models.User;
import com.example.citysites.repositories.ActivityRepository;
import com.example.citysites.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private ActivityRepository activityDao;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, ActivityRepository activityDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.activityDao = activityDao;
    }


    @GetMapping("/register")
    public String registrationPage(Model model) {
        model.addAttribute("user", new User());
        return "citysites/register";
    }

//    PostMapping for Register
    @PostMapping("/register")
    public String createUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, Principal principal) {
        User loggedInUser = userDao.findByUsername(principal.getName());
        model.addAttribute("user", loggedInUser);
        model.addAttribute("activity", activityDao.findAll());
        return "citysites/profile";
    }

    @PostMapping("/profile")
    public String editProfile(@ModelAttribute User user, Principal principal){
        User editedUser = userDao.findByUsername(principal.getName());
        editedUser.setUsername(user.getUsername());
        editedUser.setEmail(user.getEmail());
        editedUser.setProfileImageUrl(user.getProfileImageUrl());
        editedUser.setName(user.getName());
//        String hash = passwordEncoder.encode(user.getPassword());
//        editedUser.setPassword(hash);
        userDao.save(editedUser);
        if(!Objects.equals(principal.getName(), editedUser.getUsername())) {
            return "redirect:/login?logout";
        }
        return "redirect:/profile";
    }

    @GetMapping("/user/favorites")
    public String userFavoritesPage(Model model, Principal principal) {
        User loggedInUser = userDao.findByUsername(principal.getName());
        List<Activity> activityList = loggedInUser.getFavoriteActivities();
        model.addAttribute("user", loggedInUser);
        model.addAttribute("favorites", activityList);
        return "citysites/favorites";
    }

    @PostMapping("/activity/{id}/favorite")
    public String userFavorites(@PathVariable long id, Principal principal) {
//        logged in user object
        User loggedInUser = userDao.findByUsername(principal.getName());

//        need to pull out the id from the pathvariable
//        with that path variable retrieve that specific activity from the database by id
        Activity activity = activityDao.getById(id);

//        from the logged in userobject pull out the list of favorite activities and store it in a list of activities variable
        List<Activity> activityList = loggedInUser.getFavoriteActivities();

//        add the activity object from the path variable to the list of favorites activities from the user
        activityList.add(activity);

//        then youll need to set the value of the list of favorite activities on the user object that you added an activity to
        loggedInUser.setFavoriteActivities(activityList);

//        persist the change by saving the user record with the user dao
        userDao.save(loggedInUser);
        return "redirect:/user/favorites";
    }

}
