package com.example.citysites.controllers;

import com.example.citysites.models.User;
import com.example.citysites.repositories.ActivityRepository;
import com.example.citysites.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

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

    @GetMapping("/profile/{id}/{userid}")
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
        userDao.save(editedUser);
        return "redirect:/profile/{id}";
    }

    @GetMapping("/user/favorites")
    public String userFavoritesPage(Model model, Principal principal) {


        return "citysites/favorites";
    }

}
