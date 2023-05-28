package com.backend.reviewservice.controller;

import com.backend.reviewservice.document.Reward;
import com.backend.reviewservice.service.RewardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reward> getRewardById(@PathVariable("id") String id) {
        Reward reward = rewardService.getRewardById(id);
        if (reward != null) {
            return ResponseEntity.ok(reward);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Reward> createReward(@RequestBody Reward reward) {
        Reward createdReward = rewardService.createReward(reward);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReward);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reward> updateReward(@PathVariable("id") String id, @RequestBody Reward reward) {
        Reward updatedReward = rewardService.updateReward(id, reward);
        if (updatedReward != null) {
            return ResponseEntity.ok(updatedReward);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReward(@PathVariable("id") String id) {
        boolean deleted = rewardService.deleteReward(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

