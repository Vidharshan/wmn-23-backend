package com.backend.reviewservice.config;

import com.backend.reviewservice.wrapper.OpenAiApiWrapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiApiConfiguration {
    @Value("${openai.apiKey}")
    private String openAiApiKey;

    @Bean
    public OpenAiApiWrapper openAiApiWrapper() {
        return new OpenAiApiWrapper(openAiApiKey);
    }
}
