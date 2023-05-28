package com.backend.reviewservice.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.backend.reviewservice.document.Review;
import com.backend.reviewservice.enums.AttributeType;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByUserId(String userId);

    List<Review> findByProductId(String productId);

    List<Review> findByAttributesList(AttributeType attribute);
}
