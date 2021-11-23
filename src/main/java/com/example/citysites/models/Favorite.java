package com.example.citysites.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Favorite implements Serializable {
    @Column(name = "user_id")
    Long userId;
    @Column(name = "activity_id")
    Long activityId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorite favorite = (Favorite) o;
        return userId.equals(favorite.userId) && activityId.equals(favorite.activityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, activityId);
    }
}



