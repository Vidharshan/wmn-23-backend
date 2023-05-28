////package com.backend.reviewservice.service;
////
////import com.backend.reviewservice.enums.AttributeType;
////import com.google.gson.*;
////import org.apache.http.HttpEntity;
////import org.apache.http.client.methods.CloseableHttpResponse;
////import org.apache.http.client.methods.HttpPost;
////import org.apache.http.entity.ContentType;
////import org.apache.http.entity.StringEntity;
////import org.apache.http.util.EntityUtils;
////import org.springframework.http.HttpHeaders;
////import org.springframework.stereotype.Service;
////import org.apache.http.impl.client.CloseableHttpClient;
////import org.apache.http.impl.client.HttpClients;
////
////import java.io.IOException;
////import java.util.ArrayList;
////import java.util.List;
////
////@Service
////public class OpenAIService {
////
////    private static final String OPENAI_API_URL = "https://api.openai.com/v1/engines/text-davinci-003/completions";
////    private static final String OPENAI_API_KEY = "YOUR_OPENAI_API_KEY";
////
////    private final CloseableHttpClient httpClient;
////
////    public OpenAIService() {
////        this.httpClient = HttpClients.createDefault();
////    }
////
////    public List<AttributeType> getMatchingAttributeTypes(String comment) throws IOException {
////        HttpPost httpPost = new HttpPost(OPENAI_API_URL);
////        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + OPENAI_API_KEY);
////        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
////
////        JsonObject requestBody = new JsonObject();
////        requestBody.addProperty("model", "text-davinci-003");
////        requestBody.addProperty("prompt", "Decide which of the following a comment's category fits into -  COMFORT,\n" +
////                "TRANSPARENCY,\n" +
////                "QUALITY,\n" +
////                "DESIGN,\n" +
////                "VERSATILITY,\n" +
////                "STYLE,\n" +
////                "MAINTENANCE,\n" +
////                "FUNCTIONALITY,\n" +
////                "SIZING_AND_FIT,\n" +
////                "COLOUR_AND_PATTERN\n" +
////                ". Return the names of all the categories it is matching, if any.\n\nComment: \"" + comment + "\"\nCategories:");
////        requestBody.addProperty("temperature", 0.7);
////        requestBody.addProperty("max_tokens", 257);
////        requestBody.addProperty("top_p", 1);
////        requestBody.addProperty("frequency_penalty", 0.5);
////        requestBody.addProperty("presence_penalty", 0);
////
////        StringEntity requestEntity = new StringEntity(requestBody.toString(), ContentType.APPLICATION_JSON);
////        httpPost.setEntity(requestEntity);
////
////        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
////            HttpEntity responseEntity = response.getEntity();
////            String completionResponse = EntityUtils.toString(responseEntity);
////            return parseCompletionResponse(completionResponse);
////        }
////    }
////
//////    private List<AttributeType> parseCompletionResponse(String completionResponse) {
//////        List<AttributeType> attributeTypes = new ArrayList<>();
//////
//////        // Parse the completion response to extract the matching attribute types
//////        // Modify this logic based on the actual response format from OpenAI
//////
//////        // Example logic:
//////        JsonObject responseObject = JsonParser.parseString(completionResponse).getAsJsonObject();
//////        JsonArray choicesArray = responseObject.getAsJsonArray("attributesList");
//////
//////        for (JsonElement choiceElement : choicesArray) {
//////            JsonObject choiceObject = choiceElement.getAsJsonObject();
//////            String category = choiceObject.get("text").getAsString();
//////            attributeTypes.add(AttributeType.valueOf(category));
//////        }
//////
//////        return attributeTypes;
//////    }
//////    private List<AttributeType> parseCompletionResponse(String completionResponse) {
//////        List<AttributeType> attributeTypes = new ArrayList<>();
//////
//////        // Parse the completion response to extract the matching attribute types
//////        // Modify this logic based on the actual response format from OpenAI
//////
//////        // Example logic:
//////        JsonObject responseObject = JsonParser.parseString(completionResponse).getAsJsonObject();
//////        String choicesText = responseObject.get("choices").getAsJsonArray().get(0).getAsJsonObject().get("text").getAsString();
//////        String[] attributeTypeStrings = choicesText.split(",\\s*");
//////
//////        for (String attributeTypeString : attributeTypeStrings) {
//////            attributeTypes.add(AttributeType.valueOf(attributeTypeString));
//////        }
//////
//////        return attributeTypes;
//////    }
////
//////    private List<AttributeType> parseCompletionResponse(String completionResponse) {
//////        List<AttributeType> attributeTypes = new ArrayList<>();
//////
//////        JsonObject responseObject = JsonParser.parseString(completionResponse).getAsJsonObject();
//////
//////        if (responseObject.has("choices")) {
//////            JsonArray choicesArray = responseObject.getAsJsonArray("choices");
//////
//////            for (JsonElement choiceElement : choicesArray) {
//////                JsonObject choiceObject = choiceElement.getAsJsonObject();
//////                String category = choiceObject.get("text").getAsString();
//////                attributeTypes.add(AttributeType.valueOf(category));
//////            }
//////        }
//////
//////        return attributeTypes;
//////    }
////
//////    private List<AttributeType> parseCompletionResponse(String completionResponse) {
//////        List<AttributeType> attributeTypes = new ArrayList<>();
//////
//////        JsonObject responseObject = JsonParser.parseString(completionResponse).getAsJsonObject();
//////
//////        if (responseObject.has(AttributeType.QUALITY.toString())) {
//////            JsonArray choicesArray = responseObject.getAsJsonArray(AttributeType.QUALITY.toString());
//////
//////            for (JsonElement choiceElement : choicesArray) {
//////                String category = choiceElement.getAsString();
//////                attributeTypes.add(AttributeType.valueOf(category));
//////            }
//////        }
//////
//////        return attributeTypes;
//////    }
////
////    private List<AttributeType> parseCompletionResponse(String completionResponse) {
////        List<AttributeType> attributeTypes = new ArrayList<>();
////
////        JsonObject responseObject = JsonParser.parseString(completionResponse).getAsJsonObject();
////
////        if (responseObject.has("AttributeType")) {
////            JsonArray choicesArray = responseObject.getAsJsonArray("AttributeType");
////
////            for (JsonElement choiceElement : choicesArray) {
////                JsonObject choiceObject = choiceElement.getAsJsonObject();
////                String category = choiceObject.get("text").getAsString();
////                attributeTypes.add(AttributeType.valueOf(category));
////            }
////        }
////
////        return attributeTypes;
////    }
////
////
////}
////


package com.backend.reviewservice.service;

import com.backend.reviewservice.enums.AttributeType;
import com.google.gson.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OpenAIService {

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/completions";
    private static final String OPENAI_API_KEY = "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";

    private final CloseableHttpClient httpClient;

    public OpenAIService() {
        this.httpClient = HttpClients.createDefault();
    }

    public List<AttributeType> getMatchingAttributeTypes(String comment, List<AttributeType> attributeTypesList) throws IOException {
        HttpPost httpPost = new HttpPost(OPENAI_API_URL);
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + OPENAI_API_KEY);
        httpPost.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("model", "text-davinci-003");
        requestBody.addProperty("prompt", "Decide which of the following a comment's category fits into -  COMFORT,\n" +
                "TRANSPARENCY,\n" +
                "QUALITY,\n" +
                "DESIGN,\n" +
                "VERSATILITY,\n" +
                "STYLE,\n" +
                "MAINTENANCE,\n" +
                "FUNCTIONALITY,\n" +
                "SIZING_AND_FIT,\n" +
                "COLOUR_AND_PATTERN\n" +
                ". Return the names of all the categories it is matching, if any.\n\nComment: \"" + comment + "\"\nCategories:");
        requestBody.addProperty("temperature", 0);
        requestBody.addProperty("max_tokens", 60);
        requestBody.addProperty("top_p", 1);
        requestBody.addProperty("frequency_penalty", 0.5);
        requestBody.addProperty("presence_penalty", 0);

        StringEntity requestEntity = new StringEntity(requestBody.toString(), ContentType.APPLICATION_JSON);
        httpPost.setEntity(requestEntity);

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            HttpEntity responseEntity = response.getEntity();
            String completionResponse = EntityUtils.toString(responseEntity);
            return extractMatchingAttributeTypes(completionResponse, attributeTypesList);
        }
    }

    private List<AttributeType> extractMatchingAttributeTypes(String completionResponse, List<AttributeType> attributeTypesList) {
        List<AttributeType> matchingAttributeTypes = new ArrayList<>();

        JsonObject responseObject = JsonParser.parseString(completionResponse).getAsJsonObject();
        JsonArray choicesArray = responseObject.getAsJsonArray();

        for (JsonElement choiceElement : choicesArray) {
            JsonObject choiceObject = choiceElement.getAsJsonObject();
            String category = choiceObject.get("text").getAsString();

            // Check if the category is present in the attributeTypesList
            for (AttributeType attributeType : attributeTypesList) {
                if (category.equalsIgnoreCase(attributeType.name())) {
                    matchingAttributeTypes.add(attributeType);
                    break;
                }
            }
        }

        return matchingAttributeTypes;
    }

    private List<AttributeType> parseCompletionResponse(String completionResponse) {
        List<AttributeType> attributeTypes = new ArrayList<>();

        JsonObject responseObject = gson.fromJson(completionResponse, JsonObject.class);
        JsonArray choicesArray = responseObject.getAsJsonArray("choices");

        for (JsonElement choiceElement : choicesArray) {
            JsonObject choiceObject = choiceElement.getAsJsonObject();
            String category = choiceObject.get("text").getAsString();
            attributeTypes.addAll(getMatchingAttributeTypes(category));
        }

        return attributeTypes;
    }

    public List<AttributeType> getMatchingAttributeTypes(String category) {
        List<AttributeType> matchingAttributeTypes = new ArrayList<>();
        List<AttributeType> allAttributeTypes = Arrays.asList(AttributeType.values());

        for (AttributeType attributeType : allAttributeTypes) {
            if (category.contains(attributeType.toString())) {
                matchingAttributeTypes.add(attributeType);
            }
        }

        return matchingAttributeTypes;
    }
}
