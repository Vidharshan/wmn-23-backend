package com.backend.reviewservice.repository;
import com.backend.reviewservice.enums.AttributeType;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.backend.reviewservice.document.Attribute;

import java.util.List;

public interface AttributeRepository extends MongoRepository<Attribute, String> {
    List<Attribute> findByRatingGreaterThan(int rating);

    List<Attribute> findByPointsLessThan(int points);

    long countByAttributeType(AttributeType attributeType);

}
