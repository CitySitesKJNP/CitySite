package com.example.citysites.controllers;

import com.example.citysites.models.Review;
import com.example.citysites.models.User;
import com.example.citysites.repositories.ReviewRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {
    ReviewRepository reviewDao;

    public ReviewController(ReviewRepository reviewDao) {
        this.reviewDao = reviewDao;
    }

    @GetMapping("/reviews")
    public String reviewsPage() {
        return "citysites/view-reviews";
    }

    @GetMapping("/reviews/create")
    public String reviewCreationPage(Model model) {
        model.addAttribute("review", new Review());
        return "citysites/add-review";
    }

    @PostMapping("/reviews/create")
    public String createReview(@ModelAttribute Review review) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        review.setUserReview(principal);
        reviewDao.save(review);
        return "redirect:/reviews";
    }
}
