package com.backend.reviewservice.document;

import com.backend.reviewservice.enums.AttributeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection="review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String comment;
    @NonNull
    private String userId;
    @NonNull
    private String productId;

    private Integer upvotes;

    private List<AttributeType> tags;

    private List<Attribute> attributesList;
}
