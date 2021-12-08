package com.example.citysites.models;

import javax.persistence.*;

@Entity
@Table(name = "activity_images")
public class ActivityImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @ManyToOne
    @JoinColumn (name = "activity_id")
    private Activity activityImage;


    public ActivityImage() {
    }

    public ActivityImage(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public Activity getActivity() {
        return activityImage;
    }

    public void setActivity(Activity activity) {
        this.activityImage = activity;
    }

    public Activity getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(Activity activityImage) {
        this.activityImage = activityImage;
    }
}
