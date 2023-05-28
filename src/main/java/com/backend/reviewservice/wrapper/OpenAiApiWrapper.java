package com.backend.reviewservice.wrapper;

public class OpenAiApiWrapper {
    private final String apiKey;

    public OpenAiApiWrapper(String apiKey) {
        this.apiKey = apiKey;
    }

    public OpenAiApi getApiInstance() {
        return new OpenAiApi(apiKey);
    }
}
