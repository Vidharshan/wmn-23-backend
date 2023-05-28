package com.backend.reviewservice.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection="reward")
public class Reward {
    private Boolean newUser;
    private Integer relevance;
    private Integer consistency;
    private Integer reviewRequirement;
    private Integer commentLength;
    private Integer otherUserEngagement;

}
