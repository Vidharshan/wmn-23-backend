package com.backend.reviewservice.service;

import com.backend.reviewservice.document.Review;
import com.backend.reviewservice.document.Reward;
import com.backend.reviewservice.enums.AttributeType;

import java.util.List;


public interface RewardService {
    Reward getRewardById(String id);
    Reward createReward(Reward reward);
    Reward updateReward(String id, Reward reward);
    boolean deleteReward(String id);

    Integer calculateTotalReward(List<AttributeType> attributeTypeList, Review review);
}


