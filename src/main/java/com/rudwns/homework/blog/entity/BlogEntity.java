package com.rudwns.homework.blog.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class BlogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;
    private String authorEmail;

    public BlogEntity(String title, String content, String authorEmail) {
        this.title = title;
        this.content = content;
        this.authorEmail = authorEmail;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}