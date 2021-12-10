package com.example.citysites.models;

import com.sun.istack.NotNull;
import jdk.jfr.Name;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank(message= "Enter your name")
    private String name;

    @Column(nullable = false)
    @NotBlank(message= "Enter your email address")
    @Email(message= "Please enter your email address")
    private String email;

    @Column(nullable = false, length = 25, unique = true)
    @NotBlank(message= "Enter your username")
    private String username;

    @Column(nullable = false)
    @NotBlank(message= "Enter your password")
    @Length(min=6, message= "Password must be at least 6 characters in length")
    private String password;

    @Column
    private boolean isAdmin;

    @Column(columnDefinition = "TEXT")
    private String profileImageUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userReview")
    private List<Review> userReviews;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "userFavorites")
    private List<Activity> favoriteActivities;

    public User() {
    }

    public User(String name, String email, String username, String password) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        name = copy.name;
        email = copy.email;
        username = copy.username;
        password = copy.password;
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

    public User(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password +
                '}';
    }
}
