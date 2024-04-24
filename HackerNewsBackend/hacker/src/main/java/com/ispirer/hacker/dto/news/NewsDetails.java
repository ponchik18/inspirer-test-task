package com.ispirer.hacker.dto.news;

import com.ispirer.hacker.dto.comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsDetails {
    private Long id;
    private Long time;
    private Integer score;
    private String by;
    private String title;
    private String url;
    private Integer descendants;
    private List<Comment> comments;
}
