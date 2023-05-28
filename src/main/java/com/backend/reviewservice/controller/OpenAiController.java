//package com.backend.reviewservice.controller;
//
//import com.backend.reviewservice.entity.CompletionResponse;
//import com.backend.reviewservice.wrapper.OpenAiApi;
//import com.backend.reviewservice.wrapper.OpenAiApiWrapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.AsyncRestTemplate;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//
//@RestController
//@RequestMapping("/reward")
//public class OpenAiController {
//
//    @Autowired
//    private OpenAiApiWrapper openAiApiWrapper;
//
//    @Value("${openai.apiKey}")
//    private String openAiApiKey;
//
//    @PostMapping("/classify")
//    public ResponseEntity<List<String>> classifyText(@RequestBody String comment) {
//        try {
//            OpenAiApi api = openAiApiWrapper.getApiInstance();
//
//            String prompt = "{\n" +
//                    "  \"model\": \"text-davinci-003\",\n" +
//                    "  \"prompt\": \"Decide which of the following a comment's category fits into -  COMFORT,\n" +
//                    "    TRANSPARENCY,\n" +
//                    "    QUALITY,\n" +
//                    "    DESIGN,\n" +
//                    "    VERSATILITY,\n" +
//                    "    STYLE,\n" +
//                    "    MAINTENANCE,\n" +
//                    "    FUNCTIONALITY,\n" +
//                    "    SIZING_AND_FIT,\n" +
//                    "    COLOUR_AND_PATTERN\n" +
//                    ". Return the names of all the categories it is matching, if any.\\n\\nComment: \\\"" + comment + "\\\"\\nCategories: Transparency, Quality, Design, Maintenance, and Functionality\",\n" +
//                    "  \"temperature\": 0,\n" +
//                    "  \"max_tokens\": 60,\n" +
//                    "  \"top_p\": 1,\n" +
//                    "  \"frequency_penalty\": 0.5,\n" +
//                    "  \"presence_penalty\": 0\n" +
//                    "}";
//
//            var response = api.createCompletion(prompt)
//                    .setModel("text-davinci-003")
//                    .setMaxTokens(1
//
//                    )
//                    .setN(1)
//                    .execute();
//
//            List<String> categories = Arrays.asList(response.().get(0).getText().trim().split(","));
//            return ResponseEntity.ok(categories);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonList("Error occurred: " + e.getMessage()));
//        }
//    }
//
//    @Async
//    @PostMapping("/categorize")
//    public CompletableFuture<ResponseEntity<String>> categorizeComment(@RequestBody String comment) {
//        try {
//            AsyncRestTemplate asyncRestTemplate = new AsyncRestTemplate();
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set("Authorization", "Bearer " + openAiApiKey);
//
//            String prompt = "{\n" +
//                    "  \"model\": \"text-davinci-003\",\n" +
//                    "  \"prompt\": \"Decide which of the following a comment's category fits into -  COMFORT,\n" +
//                    "    TRANSPARENCY,\n" +
//                    "    QUALITY,\n" +
//                    "    DESIGN,\n" +
//                    "    VERSATILITY,\n" +
//                    "    STYLE,\n" +
//                    "    MAINTENANCE,\n" +
//                    "    FUNCTIONALITY,\n" +
//                    "    SIZING_AND_FIT,\n" +
//                    "    COLOUR_AND_PATTERN\n" +
//                    ". Return the names of all the categories it is matching, if any.\\n\\nComment: \\\"" + comment + "\\\"\\nCategories: COMFORT, TRANSPARENCY, QUALITY, DESIGN, VERSATILITY, STYLE, MAINTENANCE, FUNCTIONALITY, SIZING_AND_FIT, COLOUR_AND_PATTERN\",\n" +
//                    "  \"temperature\": 0,\n" +
//                    "  \"max_tokens\": 60,\n" +
//                    "  \"top_p\": 1,\n" +
//                    "  \"frequency_penalty\": 0.5,\n" +
//                    "  \"presence_penalty\": 0\n" +
//                    "}";
//
//            String apiUrl = "https://api.openai.com/v1/engines/davinci/completions";
//
//            String requestBody = prompt;
//
//            CompletableFuture<CompletionResponse> completionResponseFuture = asyncRestTemplate.postForEntity(apiUrl, new HttpEntity<>(requestBody, headers), CompletionResponse.class)
//                    .thenApply(ResponseEntity::getBody);
//
//            return completionResponseFuture.thenApply(response -> {
//                String category = response.getAttributeTypeList().get(0).trim();
//                return ResponseEntity.ok(category);
//            });
//        }
//
