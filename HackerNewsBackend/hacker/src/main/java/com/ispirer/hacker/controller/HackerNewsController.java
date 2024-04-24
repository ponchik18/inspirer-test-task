package com.ispirer.hacker.controller;

import com.ispirer.hacker.dto.news.News;
import com.ispirer.hacker.dto.news.NewsDetails;
import com.ispirer.hacker.service.HackerNewsService;
import com.ispirer.hacker.utlis.ListOfDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class HackerNewsController {
    private final HackerNewsService hackerNewsService;

    @GetMapping
    public ListOfDto<News> getAllLatestNews() {
        return hackerNewsService.getAllLatestNews();
    }

    @GetMapping("/{id}")
    public NewsDetails getNewsById(@PathVariable Long id) {
        return hackerNewsService.getNewsById(id);
    }
}
