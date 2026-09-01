package com.rudwns.homework.domain.blog.dto.response;

import com.rudwns.homework.domain.blog.entity.BlogEntity;
import lombok.Getter;

@Getter
public class BlogResponse {

    private Long id;
    private String title;
    private String content;
    private String authorEmail;

    public BlogResponse(BlogEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.authorEmail = entity.getAuthorEmail();
    }
}