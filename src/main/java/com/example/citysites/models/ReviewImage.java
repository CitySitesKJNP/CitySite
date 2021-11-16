package com.example.citysites.models;

import javax.persistence.*;

@Entity
@Table(name = "review_images")
public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String imageUrl;

    @ManyToOne
    @JoinColumn (name = "review_id")
    private Review reviewImage;


    public ReviewImage() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Review getReviewImage() {
        return reviewImage;
    }

    public void setReviewImage(Review reviewImage) {
        this.reviewImage = reviewImage;
    }
}
