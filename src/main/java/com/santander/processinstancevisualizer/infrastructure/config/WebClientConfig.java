package com.santander.processinstancevisualizer.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final ApiProperties apiProperties;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(apiProperties.getBaseUrl())
                .build();
    }
}
