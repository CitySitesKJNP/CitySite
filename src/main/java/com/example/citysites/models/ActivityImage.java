package com.example.citysites.models;

import javax.persistence.*;

@Entity
@Table(name = "activity_images")
public class ActivityImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String imageUrl;

//    Activity_ID here


    public ActivityImage() {
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
}
