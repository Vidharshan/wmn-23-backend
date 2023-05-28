package com.backend.reviewservice.entity;

import com.backend.reviewservice.document.Attribute;
import com.backend.reviewservice.enums.AttributeType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompletionResponse {
    private String id;
    private List<AttributeType> attributeTypeList;
}
