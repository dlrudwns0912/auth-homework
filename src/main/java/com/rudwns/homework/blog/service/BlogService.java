package com.rudwns.homework.blog.service;

import com.rudwns.homework.blog.dto.request.BlogRequest;
import com.rudwns.homework.blog.dto.response.BlogResponse;
import com.rudwns.homework.blog.entity.BlogEntity;
import com.rudwns.homework.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BlogService {

    private final BlogRepository blogRepository;

    @Transactional
    public BlogResponse createBlog(BlogRequest req, String email) {
        BlogEntity blog = new BlogEntity(req.getTitle(), req.getContent(), email);
        return new BlogResponse(blogRepository.save(blog));
    }

    public List<BlogResponse> getAllBlogs() {
        return blogRepository.findAll().stream()
                .map(BlogResponse::new)
                .toList();
    }

    public BlogResponse getBlog(Long id) {
        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        return new BlogResponse(blog);
    }

    @Transactional
    public BlogResponse updateBlog(Long id, BlogRequest req, String email) {
        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (!blog.getAuthorEmail().equals(email)) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        blog.update(req.getTitle(), req.getContent());
        return new BlogResponse(blog);
    }

    @Transactional
    public void deleteBlog(Long id, String email) {
        BlogEntity blog = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        if (!blog.getAuthorEmail().equals(email)) {
            throw new IllegalArgumentException("삭제 권한이 없습니다.");
        }

        blogRepository.delete(blog);
    }
}