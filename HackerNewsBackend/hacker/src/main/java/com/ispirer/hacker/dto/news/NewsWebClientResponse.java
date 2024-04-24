package com.ispirer.hacker.dto.news;

import java.time.LocalDateTime;
import java.util.List;

public record NewsWebClientResponse(
        Long id,
        Long time,
        Integer score,
        String by,
        String title,
        String url,
        Integer descendants,
        List<Long> kids
) {
}
