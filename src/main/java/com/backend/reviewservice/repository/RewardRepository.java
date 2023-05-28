package com.backend.reviewservice.repository;

import com.backend.reviewservice.document.Reward;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends MongoRepository<Reward, String> {

    List<Reward> findByNewUser(boolean newUser);

    List<Reward> findByRelevanceGreaterThanEqual(int relevance);

    List<Reward> findByConsistencyLessThan(int consistency);

    List<Reward> findByCommentLengthBetween(int minCommentLength, int maxCommentLength);

    List<Reward> findByOtherUserEngagementIsNull();
}

