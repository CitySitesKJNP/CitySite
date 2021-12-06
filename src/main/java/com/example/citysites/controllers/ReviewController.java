package com.example.citysites.controllers;

import com.example.citysites.models.*;
import com.example.citysites.repositories.ActivityRepository;
import com.example.citysites.repositories.ReviewRepository;
import com.example.citysites.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        Activity activity = activityDao.getById(activity_id);
        List<Review> reviews = activity.getActivityReviews();
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
    public String createReview(@PathVariable long activity_id, @ModelAttribute Review review, @RequestParam List<String> urls) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Activity activity = activityDao.getById(activity_id);
        List<ReviewImage> images = new ArrayList<>();

        for (String url : urls) {
            ReviewImage reviewImage = new ReviewImage(url);
            reviewImage.setReviewImage(review);
            images.add(reviewImage);
        }

        review.setActivityReview(activity);
        review.setUserReview(principal);
        review.setReviewImages(images);

        reviewDao.save(review);
        return "redirect:/reviews/" + activity.getId();
    }
}
