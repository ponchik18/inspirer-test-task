package com.ispirer.hacker.webclient;

import com.ispirer.hacker.dto.comment.CommentWebClientResponse;
import com.ispirer.hacker.dto.news.News;
import com.ispirer.hacker.dto.news.NewsWebClientResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class HackerNewsWebClient {
    private final WebClient webClient;

    public List<Long> getLatestNewsIndex() {
        return webClient.get()
                .uri("/newstories.json?limitToFirst=100&orderBy=\"$key\"")
                .retrieve()
                .bodyToFlux(Long.class)
                .collectList()
                .block();
    }

    public NewsWebClientResponse getNewsById(Long id) {
        return webClient.get()
                .uri("/item/{id}.json", id)
                .retrieve()
                .bodyToMono(NewsWebClientResponse.class)
                .block();
    }

    public CommentWebClientResponse getCommentById(Long id) {
        return webClient.get()
                .uri("/item/{id}.json", id)
                .retrieve()
                .bodyToMono(CommentWebClientResponse.class)
                .block();
    }
}
