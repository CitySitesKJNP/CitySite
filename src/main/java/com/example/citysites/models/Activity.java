package com.example.citysites.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "activities")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String hours;

    @Column
    private String description;

    @Column
    private float longitude;

    @Column
    private float latitude;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityImage")
    @JsonIgnore
//    @JsonManagedReference(value = "activity-images")
    private List<ActivityImage> activityImages;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityReview")
    @JsonManagedReference(value = "activity-reviews")
    private List<Review> activityReviews;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "favorites",
            joinColumns = {@JoinColumn(name = "activity_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    @JsonIgnore
    private List<User> userFavorites;


    public Activity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float logitude) {
        this.longitude = logitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public List<ActivityImage> getActivityImages() {
        return activityImages;
    }

    public void setActivityImages(List<ActivityImage> activityImages) {
        this.activityImages = activityImages;
    }

    public List<Review> getActivityReviews() {
        return activityReviews;
    }

    public void setActivityReviews(List<Review> activityReviews) {
        this.activityReviews = activityReviews;
    }

    public List<User> getUserFavorites() {
        return userFavorites;
    }

    public void setUserFavorites(List<User> userFavorites) {
        this.userFavorites = userFavorites;
    }
}
