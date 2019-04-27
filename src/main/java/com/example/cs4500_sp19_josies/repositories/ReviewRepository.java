package com.example.cs4500_sp19_josies.repositories;

import com.example.cs4500_sp19_josies.models.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository
        extends CrudRepository<Review, Integer> {
    @Query(value = "SELECT entity FROM Review entity")
    public List<Review> findAllReviews();

    @Query(value = "SELECT entity FROM Review entity WHERE id=:id")
    public Review findReviewById(
            @Param("id") Integer id);
}
