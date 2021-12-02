package com.example.citysites.repositories;

import com.example.citysites.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findById(long activity_id);
}
