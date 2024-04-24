package com.ispirer.hacker.dto.news;

public record News(
        Long id,
        Long time,
        Integer score,
        String by,
        String title
) {
}
