package com.backend.reviewservice.service.impl;

import com.backend.reviewservice.document.Review;
import com.backend.reviewservice.document.Reward;
import com.backend.reviewservice.enums.AttributeType;
import com.backend.reviewservice.repository.AttributeRepository;
import com.backend.reviewservice.repository.RewardRepository;
import com.backend.reviewservice.service.RewardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardServiceImpl implements RewardService {

    private final RewardRepository rewardRepository;

    private final AttributeRepository attributeRepository;

    public RewardServiceImpl(RewardRepository rewardRepository, AttributeRepository attributeRepository) {
        this.rewardRepository = rewardRepository;
        this.attributeRepository = attributeRepository;
    }

    @Override
    public Reward getRewardById(String id) {
        return rewardRepository.findById(id).orElse(null);
    }

    @Override
    public Reward createReward(Reward reward) {
        return rewardRepository.save(reward);
    }

    @Override
    public Reward updateReward(String id, Reward reward) {
        Reward existingReward = rewardRepository.findById(id).orElse(null);
        if (existingReward != null) {
            // Update the fields of the existingReward with the values from the reward parameter
            existingReward.setNewUser(reward.getNewUser());
            existingReward.setRelevance(reward.getRelevance());
            existingReward.setConsistency(reward.getConsistency());
            existingReward.setReviewRequirement(reward.getReviewRequirement());
            existingReward.setCommentLength(reward.getCommentLength());
            existingReward.setOtherUserEngagement(reward.getOtherUserEngagement());
            return rewardRepository.save(existingReward);
        }
        return null;
    }

    @Override
    public boolean deleteReward(String id) {
        Reward existingReward = rewardRepository.findById(id).orElse(null);
        if (existingReward != null) {
            rewardRepository.delete(existingReward);
            return true;
        }
        return false;
    }

    @Override
    public Integer calculateTotalReward(List<AttributeType> attributeTypeList, Review review) {
        Integer RELEVANCE_WEIGHT = 10;
        Integer REQUIREMENT_WEIGHT = 10;
        int NEW_USER_WEIGHT = 10;
        Integer CONSISTENCY_REWARD = 10;
        Integer OTHER_USER_ENGAGEMENT_REWARD = 10;
        Integer COMMENT_LENGTH_REWARD = 10;

        int totalReward = NEW_USER_WEIGHT;
        totalReward += RELEVANCE_WEIGHT * calculateRelevance(attributeTypeList);
        totalReward += REQUIREMENT_WEIGHT * calculateReviewRequirement(attributeTypeList);
        totalReward += OTHER_USER_ENGAGEMENT_REWARD*review.getUpvotes();
        totalReward += calculateCommentLengthReward(review, COMMENT_LENGTH_REWARD);

        return totalReward;
    }

    private Integer calculateRelevance(List<AttributeType> attributeTypeList) {
        int noOfAttributes = attributeTypeList.size();
        int totalNoOfAttributes = 12;
        return (noOfAttributes / totalNoOfAttributes);

    }

    private Integer calculateReviewRequirement(List<AttributeType> attributeTypeList) {
        int REQUIRED_NUMBER_OF_COMMENTS = 5;
        int totalComments = 0;
        for (AttributeType attributeType : attributeTypeList) {
            long commentCount = REQUIRED_NUMBER_OF_COMMENTS -attributeRepository.countByAttributeType(attributeType);
            if(commentCount<0)
                commentCount=0;
            totalComments += commentCount;
        }
        if(totalComments==0)
            return 1;
        return totalComments;

    }

    private Integer calculateCommentLengthReward(Review review, Integer COMMENT_LENGTH_REWARD) {
        int MAX_CAP_REWARD = 14000;
        if(review.getComment() == null)
            return 0;
        int min_comment_length = 5;
        int additional_reward = (review.getComment().length() - min_comment_length) *COMMENT_LENGTH_REWARD;
        return Math.min(additional_reward, MAX_CAP_REWARD);

    }
}

