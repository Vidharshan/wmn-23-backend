package com.backend.reviewservice.controller;

import com.backend.reviewservice.document.Review;
import com.backend.reviewservice.dto.CreateReviewResponse;
import com.backend.reviewservice.enums.AttributeType;
import com.backend.reviewservice.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // Create a new review
    @PostMapping
    public CreateReviewResponse createReview(@RequestBody Review review) throws IOException {
        return reviewService.createReview(review);
    }

    // Get a review by ID
    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable String id) {
        return reviewService.getReviewById(id);
    }

    // Update a review
    @PutMapping("/{id}")
    public Review updateReview(@PathVariable String id, @RequestBody Review review) {
        return reviewService.updateReview(id, review);
    }

    // Delete a review
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable String id) {
        reviewService.deleteReview(id);
    }

    // Get all reviews for a specific user
    @GetMapping("/user/{userId}")
    public List<Review> getReviewsByUserId(@PathVariable String userId) {
        return reviewService.getReviewsByUserId(userId);
    }

    // Get all reviews for a specific product
    @GetMapping("/product/{productId}")
    public List<Review> getReviewsByProductId(@PathVariable String productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    // Get all reviews with a specific attribute
    @GetMapping("/attribute/{attributeType}")
    public List<Review> getReviewsByAttribute(@PathVariable AttributeType attributeType) {
        return reviewService.getReviewsByAttribute(attributeType);
    }
}

