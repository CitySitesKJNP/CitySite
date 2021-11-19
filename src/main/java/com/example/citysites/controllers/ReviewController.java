package com.example.citysites.controllers;

import com.example.citysites.models.Review;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReviewController {
    @GetMapping("/reviews")
    public String reviewsPage() {
        return "citysites/view-reviews";
    }

    @GetMapping("/reviews/create")
    public String reviewCreationPage(Model model) {
        model.addAttribute("review", new Review());
        return "citysites/add-review";
    }

//    PostMapping for Review Creation
}
