package com.example.cs4500_sp19_josies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.cs4500_sp19_josies.models.Review;
import com.example.cs4500_sp19_josies.repositories.ReviewRepository;

@RestController
@CrossOrigin(origins = "*")
public class ReviewService {
    @Autowired
    ReviewRepository repository;

    @GetMapping("/api/reviews")
    public List<Review> findAllReviews() {
        return repository.findAllReviews();
    }

    @GetMapping("/api/reviews/{reviewId}")
    public Review findReviewById(@PathVariable("reviewId") Integer id) {
        return repository.findReviewById(id);
    }

    @PostMapping("/api/reviews")
    public Review createReview(@RequestBody Review review) {
        return repository.save(review);
    }

    @PutMapping("/api/review/{reviewId}")
    public Review updateReview(@PathVariable("reviewId") Integer id, @RequestBody Review reviewUpdates) {
        Review review = repository.findReviewById(id);
        review.setDescription(reviewUpdates.getDescription());
        return repository.save(review);
    }

    @DeleteMapping("/api/reviews/{reviewId}")
    public void deleteReview(@PathVariable("reviewId") Integer id) {
        repository.deleteById(id);
    }

}
