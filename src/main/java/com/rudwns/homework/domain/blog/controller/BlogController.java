package com.rudwns.homework.domain.blog.controller;

import com.rudwns.homework.domain.blog.dto.request.BlogRequest;
import com.rudwns.homework.domain.blog.dto.response.BlogResponse;
import com.rudwns.homework.domain.blog.service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @PostMapping
    public ResponseEntity<BlogResponse> create(
            @Valid @RequestBody BlogRequest blogRequest,
            @AuthenticationPrincipal String email
    ) {
        BlogResponse blogResponse = blogService.createBlog(blogRequest, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(blogResponse);
    };

    @GetMapping
    public ResponseEntity<List<BlogResponse>> getAllBlogs() {
        List<BlogResponse> responses = blogService.getAllBlogs();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogResponse> getBlog(@PathVariable Long id) {
        BlogResponse response = blogService.getBlog(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogResponse> updateBlog(
            @PathVariable Long id,
            @RequestBody BlogRequest request,
            @AuthenticationPrincipal String email
    ) {
        BlogResponse response = blogService.updateBlog(id, request, email);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlog(
            @PathVariable Long id,
            @AuthenticationPrincipal String email
    ) {
        blogService.deleteBlog(id, email);
        return ResponseEntity.noContent().build();
    }
}