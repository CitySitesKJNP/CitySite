package com.example.citysites.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewController {
    @GetMapping("/reviews")
    public String reviewsPage() {
        return "citysites/view-reviews";
    }

    @GetMapping("/reviews/create")
    public String reviewCreationPage() {
        return "citysites/add-review";
    }

//    Will need PostMapping for Review Creation
}
