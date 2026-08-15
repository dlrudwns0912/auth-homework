package com.rudwns.homework.blog.repository;

import com.rudwns.homework.blog.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {
}