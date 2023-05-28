package com.backend.reviewservice.service;

import com.backend.reviewservice.document.Review;
import com.backend.reviewservice.dto.CreateReviewResponse;
import com.backend.reviewservice.enums.AttributeType;

import java.io.IOException;
import java.util.List;

public interface ReviewService {
    CreateReviewResponse createReview(Review review) throws IOException;

    Review getReviewById(String id);

    Review updateReview(String id, Review review);

    void deleteReview(String id);

    List<Review> getReviewsByUserId(String userId);

    List<Review> getReviewsByProductId(String productId);

    List<Review> getReviewsByAttribute(AttributeType attributeType);
}

