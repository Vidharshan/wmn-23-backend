package com.backend.reviewservice.document;

import com.backend.reviewservice.enums.AttributeType;
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
@Document(collection="attribute")
public class Attribute {
    private AttributeType attributeType;
    private int rating;
    private int points;
}
