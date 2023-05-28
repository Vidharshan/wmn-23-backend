package com.backend.reviewservice.service.impl;

import com.backend.reviewservice.document.Review;
import com.backend.reviewservice.dto.CreateReviewResponse;
import com.backend.reviewservice.enums.AttributeType;
import com.backend.reviewservice.exceptions.ResourceNotFoundException;
import com.backend.reviewservice.repository.ReviewRepository;
import com.backend.reviewservice.service.OpenAIService;
import com.backend.reviewservice.service.ReviewService;
import com.backend.reviewservice.service.RewardService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


@Slf4j
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    private final RewardService rewardService;

    private final OpenAIService openAIService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, RewardService rewardService, OpenAIService openAIService) {
        this.reviewRepository = reviewRepository;
        this.rewardService = rewardService;
        this.openAIService = openAIService;
    }

    @Override
    public CreateReviewResponse createReview(Review review) throws IOException {
        //If comment is not null then first call open generative ai thingy and add attributes as tags
        //then call countTotalScore function here itself, you need to return both of that right
        List<AttributeType> attributeTypeList= openAIService.getMatchingAttributeTypes(review.getComment());
        log.info(attributeTypeList.toString());
        review.setTags(attributeTypeList);
        reviewRepository.save(review);
        return CreateReviewResponse.builder()
                .rewardsRecieved(rewardService.calculateTotalReward(attributeTypeList,review))
                .review(review)
                .build();

    }

    private List<AttributeType> createAttributeTypesList() {
        List<AttributeType> attributeTypesList = new ArrayList<>();
        attributeTypesList.add(AttributeType.COMFORT);
        attributeTypesList.add(AttributeType.TRANSPARENCY);
        attributeTypesList.add(AttributeType.QUALITY);
        attributeTypesList.add(AttributeType.DESIGN);
        attributeTypesList.add(AttributeType.VERSATILITY);
        attributeTypesList.add(AttributeType.STYLE);
        attributeTypesList.add(AttributeType.MAINTENANCE);
        attributeTypesList.add(AttributeType.FUNCTIONALITY);
        attributeTypesList.add(AttributeType.SIZING_AND_FIT);
        attributeTypesList.add(AttributeType.COLOUR_AND_PATTERN);

        return attributeTypesList;
    }

    @Override
    public Review getReviewById(String id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
    }

    @Override
    public Review updateReview(String id, Review review) {
        Review existingReview = getReviewById(id);
        existingReview.setComment(review.getComment());
        existingReview.setUserId(review.getUserId());
        existingReview.setProductId(review.getProductId());
        existingReview.setAttributesList(review.getAttributesList());
        return reviewRepository.save(existingReview);
    }

    @Override
    public void deleteReview(String id) {
        Review review = getReviewById(id);
        reviewRepository.delete(review);
    }

    @Override
    public List<Review> getReviewsByUserId(String userId) {
        return reviewRepository.findByUserId(userId);
    }

    @Override
    public List<Review> getReviewsByProductId(String productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public List<Review> getReviewsByAttribute(AttributeType attributeType) {
        return reviewRepository.findByAttributesList(attributeType);
    }
}

