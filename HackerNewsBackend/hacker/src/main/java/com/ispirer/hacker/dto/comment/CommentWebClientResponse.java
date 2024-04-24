package com.ispirer.hacker.dto.comment;

import java.util.List;

public record CommentWebClientResponse(
        Long id,
        String by,
        Long time,
        String text,
        List<Long> kids
) {

}
