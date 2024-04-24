package com.ispirer.hacker.service;

import com.ispirer.hacker.dto.comment.Comment;
import com.ispirer.hacker.dto.comment.CommentWebClientResponse;
import com.ispirer.hacker.dto.news.News;
import com.ispirer.hacker.dto.news.NewsDetails;
import com.ispirer.hacker.dto.news.NewsWebClientResponse;
import com.ispirer.hacker.mapper.CommentMapper;
import com.ispirer.hacker.mapper.NewsMapper;
import com.ispirer.hacker.utlis.ListOfDto;
import com.ispirer.hacker.webclient.HackerNewsWebClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HackerNewsService {

    private final HackerNewsWebClient hackerNewsWebClient;
    private final NewsMapper newsMapper;
    private final CommentMapper commentMapper;

    public ListOfDto<News> getAllLatestNews() {
        List<Long> newsIds = hackerNewsWebClient.getLatestNewsIndex();

        List<News> newsData = new ArrayList<>();
        for(Long newsId: newsIds) {
            NewsWebClientResponse news = hackerNewsWebClient.getNewsById(newsId);
            newsData.add(newsMapper.fromNewsWebClientResponseToNews(news));
        }

        return ListOfDto.<News>builder()
                .content(newsData)
                .size(newsData.size())
                .build();
    }

    public NewsDetails getNewsById(Long id) {
        NewsWebClientResponse newsWebClientResponse = hackerNewsWebClient.getNewsById(id);
        NewsDetails news = newsMapper.fromNewsDetailsWebClientResponseToNews(newsWebClientResponse);

        news.setComments(
                populateComments(newsWebClientResponse.kids())
        );

        return news;
    }

    private List<Comment> populateComments(List<Long> ids) {
        if(ids == null || ids.isEmpty()) {
            return new ArrayList<>();
        }
        List<Comment> comments = new ArrayList<>();
        for(Long id: ids) {
            CommentWebClientResponse commentWebClientResponse = hackerNewsWebClient.getCommentById(id);
            List<Comment> kidComments = populateComments(commentWebClientResponse.kids());
            Comment comment = commentMapper.fromCommentWebClientResponseToComment(commentWebClientResponse);
            comment.setComments(kidComments);
            comments.add(comment);
        }
        return comments;
    }
}
