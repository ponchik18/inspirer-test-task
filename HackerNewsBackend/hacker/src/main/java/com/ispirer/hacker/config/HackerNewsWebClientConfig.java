package com.ispirer.hacker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class HackerNewsWebClientConfig {

    @Value("${webclient.urls.hacker-news}")
    private String hackerNewBaseUrl;

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient
                .builder();
    }
    
    @Bean
    public WebClient webClient() {
        return webClientBuilder()
                .baseUrl(hackerNewBaseUrl)
                .build();
    }
}