package com.example.citysites.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String username;

    @Column
    private boolean isAdmin;

    @Column
    private String profileImageUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userReview")
    private List<Review> userReviews;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "favorites",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "activity_id")}
    )
    private List<Activity> favoriteActivities;

    public User() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public List<Review> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<Review> userReviews) {
        this.userReviews = userReviews;
    }

    public List<Activity> getFavoriteActivities() {
        return favoriteActivities;
    }

    public void setFavoriteActivities(List<Activity> favoriteActivities) {
        this.favoriteActivities = favoriteActivities;
    }
}
