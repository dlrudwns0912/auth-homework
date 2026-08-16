package com.rudwns.homework.blog.controller;

import com.rudwns.homework.blog.dto.request.BlogRequest;
import com.rudwns.homework.blog.dto.response.BlogResponse;
import com.rudwns.homework.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@RequiredArgsConstructor
public class BlogController {
    private final BlogService blogService;

    @PostMapping
    public BlogResponse createBlog(@RequestBody BlogRequest req, Authentication auth) {
        return blogService.createBlog(req, auth.getName());
    }

    @GetMapping
    public List<BlogResponse> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @GetMapping("/{id}")
    public BlogResponse getBlog(@PathVariable Long id) {
        return blogService.getBlog(id);
    }

    @PutMapping("/{id}")
    public BlogResponse updateBlog(
            @PathVariable Long id,
            @RequestBody BlogRequest req,
            Authentication auth
    ) {
        return blogService.updateBlog(id, req, auth.getName());
    }

    @DeleteMapping("/{id}")
    public String deleteBlog(@PathVariable Long id, Authentication auth) {
        blogService.deleteBlog(id, auth.getName());
        return "게시글이 삭제되었습니다.";
    }
}