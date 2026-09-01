package com.rudwns.homework.domain.blog.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor

public class BlogRequest {

    private String title;
    private String content;

    public BlogRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}