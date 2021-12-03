package com.example.citysites.controllers;

import com.example.citysites.models.Activity;
import com.example.citysites.models.Review;
import com.example.citysites.models.User;
import com.example.citysites.repositories.ActivityRepository;
import com.example.citysites.repositories.ReviewRepository;
import com.example.citysites.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReviewController {

    UserRepository usersDao;
    ReviewRepository reviewDao;
    ActivityRepository activityDao;

    public ReviewController(UserRepository usersDao, ReviewRepository reviewDao, ActivityRepository activityDao) {
        this.usersDao = usersDao;
        this.reviewDao = reviewDao;
        this.activityDao = activityDao;
    }

    @GetMapping("/reviews/{activity_id}")
    public String reviewsPage(@PathVariable long activity_id, Model model) {
        List<Review> reviews = reviewDao.findById(activity_id);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", principal);
        model.addAttribute("reviews", reviews);
        return "citysites/view-reviews";
    }

    @GetMapping("/reviews/create/{activity_id}")
    public String reviewCreationPage(@PathVariable long activity_id, Model model) {
        model.addAttribute("activity", activityDao.getById(activity_id));
        model.addAttribute("review", new Review());
        return "citysites/add-review";
    }

    @PostMapping("/reviews/create/{activity_id}")
    public String createReview(@PathVariable long activity_id, @ModelAttribute Review review) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Activity activity = activityDao.getById(activity_id);
        review.setActivityReview(activity);
        review.setUserReview(principal);
        reviewDao.save(review);
        return "redirect:/reviews/" + review.getId();
    }
}
