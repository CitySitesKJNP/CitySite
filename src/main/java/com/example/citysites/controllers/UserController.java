package com.example.citysites.controllers;

import com.example.citysites.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "citysites/login";
    }

    @GetMapping("/register")
    public String registrationPage() {
        return "citysites/register";
    }

//    PostMapping for Register

    @GetMapping("/profile")
    public String profilePage() {
        return "citysites/profile";
    }

    @GetMapping("/user/favorites")
    public String userFavoritesPage() {
        return "citysites/favorites";
    }
}
