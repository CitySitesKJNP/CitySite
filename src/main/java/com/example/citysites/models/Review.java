package com.example.citysites.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reviewImage")
    private List<ReviewImage> reviewImages;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userReview;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activityReview;

    public Review(long id, int rating, String description) {
        this.id = id;
        this.rating = rating;
        this.description = description;
    }

    public Review() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ReviewImage> getReviewImages() {
        return reviewImages;
    }

    public void setReviewImages(List<ReviewImage> reviewImages) {
        this.reviewImages = reviewImages;
    }

    public User getUserReview() {
        return userReview;
    }

    public void setUserReview(User userReview) {
        this.userReview = userReview;
    }

    public Activity getActivityReview() {
        return activityReview;
    }

    public void setActivityReview(Activity activityReview) {
        this.activityReview = activityReview;
    }
}
